package com.example.helppy.GestureDetector

import android.util.Log
import android.view.MotionEvent
import android.view.View


class TapDetector(v: View):
    android.view.GestureDetector.OnGestureListener,
    android.view.GestureDetector.OnDoubleTapListener {


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

    override fun onFling(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        return true
    }


    // 한 번 터치 이벤트
    override fun onDoubleTap(p0: MotionEvent): Boolean {
        TODO("Not yet implemented")
    }

    // 두 번 터치 이벤트
    override fun onDoubleTapEvent(p0: MotionEvent): Boolean {
        TODO("Not yet implemented")
    }

    // 두 번 터치 이벤트의 어떤 단계이 있는지
    override fun onSingleTapConfirmed(p0: MotionEvent): Boolean {
        TODO("Not yet implemented")
    }



}