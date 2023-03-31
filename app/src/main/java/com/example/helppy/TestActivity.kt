package com.example.helppy

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.helppy.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {

    lateinit var binding: ActivityTestBinding

    private lateinit var gestureDetector: GestureDetector
    var myFocusedView: View? = null

    //    var detector: GestureDetector? = null // 무슨 제스쳐를 했는지 감지
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        val myGestureListener = GestureListener(this)
//        gestureDetector = GestureDetector(this, myGestureListener)    // 반환값은 view element

//        val touchView = findViewById<View>(binding.root)
//        touchView.setOnTouchListener { _, event ->
//            gestureDetector.onTouchEvent(event)
//        }

        // TODO: if(image): ImageProcessor
//        if (touchView is ImageView) {
//            ImageProcessor(touchView)
//        }

        binding.imageView2.setOnClickListener {
            // 이미지 받아오기
//            val imageView = binding.imageView2
////
//            val imageProcessor = ImageProcessor()
//            val base64Image = imageProcessor.imageEncoder(imageView)
////            val base64Image =
////                "6382D DJEGF6 17425E CCE19g 062574 \\ud45c\\uc2dc\\uc5d0 \\uc0ac\\uc6a9\\ud558\\uae30 \\ucd9c\\uc740 \\ud14d\\uc2a4\\ud2b8 \\uae34 \\uadf8\\ub9bc\\ucc28"
//
//
//            if (base64Image != null) {
//                imageProcessor.sendRequest(base64Image, object : ImageProcessor.ResponseCallback {
//                    override fun onSuccess(responseData: String) {
//                        Log.d("Response", responseData)
//                        val response_data = imageProcessor.UnicodeToHangul(responseData)
//                        Log.d("text", response_data)
//
//                    }
//
//                    override fun onFailure(exception: IOException) {
//                        Log.e("Error", exception.message ?: "Unknown error")
//                    }
//                })
//            } else {
//                Log.e("Error", "Failed to encode the image")
//            }


        }


        // TODO: TTS


//        Log.d("gesture", "$focusedView")
//
//
//        if (focusedView is Button || focusedView is TextView) {
//
//        }
    }

    fun updateFocusedView(view: View?) {
        myFocusedView = view
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (gestureDetector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }


}