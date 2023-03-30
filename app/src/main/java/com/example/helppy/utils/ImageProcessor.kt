package com.example.helppy.utils

//import android.util.Base64
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*

class ImageProcessor() {

    interface ResponseCallback {
        fun onSuccess(responseData: String)
        fun onFailure(exception: IOException)
    }


    // 이미지 base64 변환
    fun imageEncoder(image: ImageView): String? {

        val drawable = image.drawable as? BitmapDrawable ?: run {
            println("Failed to get the BitmapDrawable from ImageView.")
            return null
        }
        val bitmap = drawable.bitmap                // image -> bitmap

        val byteArrayOutputStream = ByteArrayOutputStream()
        val compressFormat = when (drawable.bitmap.config) {
            Bitmap.Config.ARGB_8888, Bitmap.Config.RGBA_F16 -> Bitmap.CompressFormat.PNG
            else -> Bitmap.CompressFormat.JPEG
        }
        bitmap.compress(
            compressFormat,
            100,
            byteArrayOutputStream
        )

        val bytes = byteArrayOutputStream.toByteArray()  // bitmap -> bytearray
        return Base64.getEncoder().encodeToString(bytes) // bytearray -> base64

    }

    // base64값 서버로 전달
    fun sendRequest(base64: String, callback: ResponseCallback) {

        val client = OkHttpClient()
        val url = "http://35.234.33.62/img-src"
        val jsonMediaType = "application/json; charset=utf-8".toMediaType()

        val requestBody = RequestBody.create(
            jsonMediaType,
            """{"base64": "$base64"}"""
        )

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()


        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {      // 블록이 완료될 때 응답 리소스가 적절하게 닫히게 함
                    if (!response.isSuccessful) {
                        callback.onFailure(IOException("Unexpected code $response"))
                    } else {
                        val responseData = response.body!!.string()
                        callback.onSuccess(responseData)
                    }
                }
            }
        })

    }

    fun UnicodeToHangul(responseString: String): String {
        // responseData에서 유니코드 찾기
        val word = mutableListOf<String>()
        val currentWords = mutableListOf<String>()
        val wordList = mutableListOf<String>()
        var unicodeList = ""

        val l = responseString.length
        for (i in 0..l step 1) {
            if (responseString[i] == '\\') {
                unicodeList = responseString.substring(i, l - 3)
                break
            }
        }

        val unicodeBlock = unicodeList.split(" ")

        for (block in unicodeBlock) {
            currentWords.clear()
            var uni = block.splitToSequence("\\u").map { "\\u$it" }.toList()
            uni = uni.subList(1, uni.size)

            for (u in uni) {
                currentWords.add(convertStringUnicodeToKorean(u))
            }
            wordList.add(currentWords.joinToString(""))
        }
//        Log.d("return_value", wordList.joinToString(" "))
        return wordList.joinToString(" ")

    }


    fun convertStringUnicodeToKorean(data: String): String {


        val sb = StringBuilder() // 단일 쓰레드이므로 StringBuilder 선언
        var i = 0
//
//    /**
//     * \uXXXX 로 된 아스키코드 변경
//     * i+2 to i+6 을 16진수의 int 계산 후 char 타입으로 변환
//     */
        while (i < data.length) {
            if (data[i] == '\\' && data[i + 1] == 'u') {
                val word = data.substring(i + 2, i + 6).toInt(16).toChar()
                sb.append(word)
                i += 5
            } else {
                sb.append(data[i])
            }
            i++
        }
//
        return sb.toString()
    }

}

