package com.example.helppy.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.*
import android.view.accessibility.AccessibilityEvent
import android.widget.ImageView
import android.widget.TextView
import com.example.helppy.databinding.ActivityMainBinding
import java.io.IOException
import java.util.*
import kotlin.math.abs

// TODO: Single Tap: Recognize text at the focused location
// TODO: Double Tap: Execute the button at the focused position
// TODO: Swipe right: Move focus to another item to the right
// TODO: Swipe left: move focus to another item on the left
// TODO: Two-finger swipe up and down: scroll the screen

class GestureListener(
    context: Context,
    private val view: View,
//    private val onCancel: () -> Unit
) : GestureDetector.SimpleOnGestureListener() {

    private lateinit var binding: ActivityMainBinding
    private val gestureDetector: GestureDetector = GestureDetector(context, this)
    private var focusedView: View? = null
    private var isReadingText = false
    private var isAskingToReadImageText = false

    private lateinit var tts: TextToSpeech

//    val tts = TextToSpeech(context) { status ->
//        if (status == TextToSpeech.SUCCESS) {
//            tts.language = Locale.KOREAN
//        }

    private var lastVolumeUpTimestamp: Long = 0
    private var lastVolumeDownTimestamp: Long = 0


    @SuppressLint("ClickableViewAccessibility")
    fun initGestureDetector(view: View) {
        view.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
        }
    }

//    // 화면에 손가락이 닿았을 때, 제스쳐 시작을 알림
//    override fun onDown(event: MotionEvent): Boolean {
//        Log.d("gesture", "onDown() 호출")
////        focusedView = event.view.findViewAtPosition(event.x.toInt(), event.y.toInt())
////
////        if (focusedView != null) {
////            focusedView!!.requestFocus()
////            isReadingText = focusedView is TextView
////            isAskingToReadImageText = focusedView is ImageView
////        }
//        return true
//    }

    // 화면 한 번 탭
    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        Log.d("gesture", "onSingleTapUp() 호출")
        return true
    }

    // 두 번 터치 이벤트의 어떤 단계이 있는지
    override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
        Log.d("gesture", "onSingleTapConfirmed() 호출")
        focusedView = findViewAtPosition(e.x, e.y)
        focusedView?.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
        return true
    }

    override fun onFling(
        downEvent: MotionEvent,
        upEvent: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        Log.d("gesture", "onFling() 호출")

        val swipeThreshold = 100
        val swipeVelocityThreshold = 100

        // 제스처 이벤트를 받아서 변경
        var result = false
        val deltaY = upEvent.y - downEvent.y
        val deltaX = upEvent.x - downEvent.x
        if (abs(deltaX) > abs(deltaY)) {
            if (abs(deltaX) > swipeThreshold && abs(velocityX) > swipeVelocityThreshold) {
                val prevView = focusedView?.focusSearch(View.FOCUS_LEFT)
                if (prevView != null) {
                    prevView.requestFocus()
                    focusedView = prevView
                    isReadingText = focusedView is TextView
                    isAskingToReadImageText = focusedView is ImageView


                    return true
                }
            } else {
                val nextView = focusedView?.focusSearch(View.FOCUS_RIGHT)
                if (nextView != null) {
                    nextView.requestFocus()
                    focusedView = nextView
                    isReadingText = focusedView is TextView
                    isAskingToReadImageText = focusedView is ImageView
                    return true
                }
            }
            result = true
        }
        return result
    }

    // 두 번 탭 이벤트
    override fun onDoubleTap(p0: MotionEvent): Boolean {
        Log.d("gesture", "onDoubleTap() 호출")

        if (focusedView != null) {
            focusedView!!.performClick()
            return true
        }
        return false

    }

    fun isReadingText(): Boolean {
        return isReadingText
    }

    fun isAskingToReadImageText(): Boolean {
        return isAskingToReadImageText
    }

    private fun speak(text: String, context: Context) {
        tts = TextToSpeech(context) { status ->
            if (status != TextToSpeech.ERROR) {
                tts.language = Locale.getDefault()
                tts.speak(
                    text,
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    null
                ) // QUEUE_FLUSH: 새 텍스트를 말하기 전에 음성 대기열을 지움
            }
        }
    }
//
//    fun onDestroy() {
//        tts.shutdown()
//    }

    fun onVolumeKeyPressed(keyCode: Int, focusedView: ImageView) {
        val currentTimestamp = System.currentTimeMillis()

        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            if (currentTimestamp - lastVolumeUpTimestamp < DOUBLE_PRESS_INTERVAL) {
                imageProcessor(focusedView)
            }
            lastVolumeUpTimestamp = currentTimestamp
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            if (currentTimestamp - lastVolumeDownTimestamp < DOUBLE_PRESS_INTERVAL) {
                speak("Canceled", view.context)
            }
            lastVolumeDownTimestamp = currentTimestamp
        }
    }

    companion object {
        private const val DOUBLE_PRESS_INTERVAL = 300L
    }


    private fun findViewAtPosition(
        x: Float,
        y: Float,
        viewGroup: ViewGroup? = view as? ViewGroup
    ): View? {
        if (viewGroup == null) return null

        for (i in 0 until viewGroup.childCount) {
            val child = viewGroup.getChildAt(i)
            val rect = Rect()
            child.getHitRect(rect)

            if (rect.contains(x.toInt(), y.toInt())) {
                return if (child is ViewGroup) {
                    findViewAtPosition(x - child.left, y - child.top, child) ?: child
                } else {
                    child
                }
            }
        }

        return null

    }


    private fun imageProcessor(view: ImageView) {

        // 이미지 받아오기
//        val view = focusedView

        val myImageProcessor = ImageProcessor()
        val base64Image = myImageProcessor.imageEncoder(view)

        if (base64Image != null) {
            myImageProcessor.sendRequest(base64Image, object : ImageProcessor.ResponseCallback {
                override fun onSuccess(responseData: String) {
                    Log.d("Response", responseData)
                    val response_data = myImageProcessor.UnicodeToHangul(responseData)
                    Log.d("text", response_data)
                }

                override fun onFailure(exception: IOException) {
                    Log.e("Error", exception.message ?: "Unknown error")
                }
            })
        } else {
            Log.e("Error", "Failed to encode the image")
        }
    }


}