package com.example.helppy.utils

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent

class GestureListener(): GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    // 화면에 손가락이 닿았을 때, 제스쳐 시작을 알림
    override fun onDown(p0: MotionEvent): Boolean {
        Log.d("gesture", "onDown() 호출")
        return true
    }

    // 화면이 눌렸다 떼어지는 경우, 제스처의 시작과 끝을 알림
    override fun onShowPress(p0: MotionEvent) {
        Log.d("gesture", "onShowPress() 호출")
    }

    // 화면 한 번 탭
    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        Log.d("gesture", "onSignleTapUp() 호출")
        return true
    }

    // 일정한 속도로 스크롤
    override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
        Log.d("gesture", "onScroll() 호출")
        return true
    }

    // 길게 탭
    override fun onLongPress(p0: MotionEvent) {
        Log.d("gesture", "onLongPress() 호출")
    }

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
        Log.d("gesture", "onSwipeRight() 호출")

//        TODO("다음 element로 이동 후 읽어주기")

    }
    private fun onSwipeLeft() {
        Log.d("gesture", "onSwipeLeft() 호출")

//        TODO("이전 element로 이동 후 읽어주기")

    }


    // 한 번 터치 이벤트
    override fun onDoubleTap(p0: MotionEvent): Boolean {
        Log.d("gesture", "onDoubleTap() 호출")
        return true
    }
    // 두 번 터치 이벤트
    override fun onDoubleTapEvent(p0: MotionEvent): Boolean {
        Log.d("gesture", "onDoubleTapEvent() 호출")
        return true
    }
    // 두 번 터치 이벤트의 어떤 단계이 있는지
    override fun onSingleTapConfirmed(p0: MotionEvent): Boolean {
        Log.d("gesture", "onSingleTapConfirmed() 호출")
        return true
    }



}