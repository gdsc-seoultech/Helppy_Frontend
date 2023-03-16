package com.example.helppy.GestureDetector

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

open class SwipeDetector() : GestureDetector.OnGestureListener {
    // 스와이프 감지
    override fun onFling(downEvent: MotionEvent, upEvent: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        Log.d("gesture", "onFling() 호출")

        val SWIPE_THRESHOLD = 100
        val SWIPE_VELOCITY_THRESHOLD = 100

        // 제스처 이벤트를 받아서 변경
        var result = false
        try {
            val diffY = upEvent!!.y - downEvent!!.y
            val diffX = upEvent.x - downEvent.x
            if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight()
                        } else {
                            onSwipeLeft()
                        }
                    }
                    result = true
                }
                result = true
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
            return result
        }

    private fun onSwipeRight() {
        TODO("다음 element로 이동 후 읽어주기")
    }

    private fun onSwipeLeft() {
        TODO("이전 element로 이동 후 읽어주기")
    }



    override fun onDown(p0: MotionEvent): Boolean {
        return true
    }
    override fun onShowPress(p0: MotionEvent) {
    }
    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        return true
    }
    override fun onScroll(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        return true
    }
    override fun onLongPress(p0: MotionEvent) {
    }
}

