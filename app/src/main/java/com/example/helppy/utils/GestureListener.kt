package com.example.helppy.utils

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.helppy.TestActivity
import kotlin.math.abs

// TODO: Single Tap: Recognize text at the focused location
// TODO: Double Tap: Execute the button at the focused position
// TODO: Swipe right: Move focus to another item to the right
// TODO: Swipe left: move focus to another item on the left
// TODO: Two-finger swipe up and down: scroll the screen

class GestureListener(private val activity: TestActivity): GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    // 화면 한 번 탭
    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        Log.d("gesture", "onSingleTapUp() 호출")
        return true
    }

    // 두 번 터치 이벤트의 어떤 단계이 있는지
    override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
        Log.d("gesture", "onSingleTapConfirmed() 호출")
//        val focusedView = activity.myFocusedView
        activity.updateFocusedView(findTouchedView(e.x, e.y))
        return true
    }

    override fun onFling(downEvent: MotionEvent, upEvent: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        Log.d("gesture", "onFling() 호출")

        val swipeThreshold = 100
        val swipeVelocityThreshold = 100

        // 제스처 이벤트를 받아서 변경
        var result = false
        val deltaY = upEvent.y - downEvent.y
        val deltaX = upEvent.x - downEvent.x
        if (abs(deltaX) > abs(deltaY)) {
            if (abs(deltaX) > swipeThreshold && abs(velocityX) > swipeVelocityThreshold) {
                if (deltaX > 0) {
                    onSwipeRight()
                } else {
                    onSwipeLeft()
                }
            }
            result = true
        }
        return result
    }

    // 두 번 탭 이벤트
    override fun onDoubleTap(p0: MotionEvent): Boolean {
        Log.d("gesture", "onDoubleTap() 호출")
        return true
    }
    // 아래, 위 이동 이벤트를 포함한 두 번 탭 체스처
    override fun onDoubleTapEvent(p0: MotionEvent): Boolean {
        Log.d("gesture", "onDoubleTapEvent() 호출")
        return true
    }

//    override fun onTwoFingerScroll(distanceX: Float, distanceY: Float): Boolean {
//        val scrollView = activity.findViewById<ScrollView>(R.id.myScrollView)
//        scrollView.scrollBy(distanceX.toInt(), distanceY.toInt())
//        return true
//    }



//    private fun getFocusedView(): View {
//        return focusedView
//    }


    private fun View.findViewAt(x: Float, y: Float): View? {
        val location = IntArray(2)
        getLocationOnScreen(location)

        val screenX = location[0] + x
        val screenY = location[1] + y

        val root = rootView
//        val view = root.hitTest(screenX, screenY)
        val view = root.findFocus()
        return if (view != null && view != root) {
            view
        } else { null }
    }

    private fun findTouchedView(x: Float, y: Float): View? {
        val root = activity.findViewById<View>(android.R.id.content)
        // 화면의 특정 위치에서 View를 가져옴
        val touchedView = root.findViewAt(x, y)
//        val touchedView = root.
        return touchedView?.takeIf { it.isFocusable }
    }

    private fun onSwipeRight(): Boolean {
        Log.d("gesture", "onSwipeRight() 호출")
        val nextView = getNextView(activity.myFocusedView)
        nextView?.requestFocus()
        return true
    }
    private fun onSwipeLeft(): Boolean {
        Log.d("gesture", "onSwipeLeft() 호출")
        val lastView = getLastView(activity.myFocusedView)
        lastView?.requestFocus()
        return true
    }
    private fun getNextView(currentView: View?): View? {
        if (currentView == null) {
            return null
        }
        val parentView = currentView.parent as? ViewGroup ?: return null
        val index = parentView.indexOfChild(currentView)
        val nextIndex = if (index + 1 < parentView.childCount) index + 1 else index

        return parentView.getChildAt(nextIndex)
    }

    private fun getLastView(currentView: View?): View? {
        if (currentView == null) {
            return null
        }
        val parentView = currentView.parent as? ViewGroup ?: return null
        val index = parentView.indexOfChild(currentView)
        val lastIndex = if (index - 1 >= 0) index - 1 else index

        return parentView.getChildAt(lastIndex)
    }



//     Not used

    // 일정한 속도로 스크롤
    override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
        Log.d("gesture", "onScroll() 호출")

//        val focusedView = getFocusedView()
//        val scrollThreshold = 100
//
//        if (e1 != null && e2 != null) {
//            val deltaY = e2.y - e1.y
//            if (abs(deltaY) > scrollThreshold) {
//                val scrollAmount = deltaY.toInt()
//                focusedView.scrollBy(0, scrollAmount)
//            }
//        }
        return true
    }
    // 화면에 손가락이 닿았을 때, 제스쳐 시작을 알림
    override fun onDown(p0: MotionEvent): Boolean {
        Log.d("gesture", "onDown() 호출")
        return true
    }
    // 화면이 눌렸다 떼어지는 경우, 제스처의 시작과 끝을 알림
    override fun onShowPress(p0: MotionEvent) {
        Log.d("gesture", "onShowPress() 호출")
    }
    // 길게 탭
    override fun onLongPress(p0: MotionEvent) {
        Log.d("gesture", "onLongPress() 호출")
    }


}