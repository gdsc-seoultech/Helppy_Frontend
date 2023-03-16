package com.example.helppy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.MotionEventCompat
import com.example.helppy.databinding.ActivityMainBinding
import com.example.helppy.databinding.ActivityTestBinding
import com.example.helppy.utils.GestureListener

class TestActivity : AppCompatActivity() {

    private lateinit var gestureDetector: GestureDetector

    //    var detector: GestureDetector? = null // 무슨 제스쳐를 했는지 감지
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var mygestureListener = GestureListener()
        gestureDetector = GestureDetector(this, mygestureListener)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        val action: Int = MotionEventCompat.getActionMasked(event)
//
//        return when (action) {
//            MotionEvent.ACTION_DOWN -> {
//                Log.d("gesture", "Action was DOWN")
//                true
//            }
//            MotionEvent.ACTION_MOVE -> {
//                Log.d("gesture", "Action was MOVE")
//                true
//            }
//            MotionEvent.ACTION_UP -> {
//                Log.d("gesture", "Action was UP")
//                true
//            }
//            MotionEvent.ACTION_CANCEL -> {
//                Log.d("gesture", "Action was CANCEL")
//                true
//            }
//            MotionEvent.ACTION_OUTSIDE -> {
//                Log.d("gesture", "Movement occurred outside bounds of current screen element")
//                true
//            }
//            else -> super.onTouchEvent(event)
//        }
        if (event != null) {
            gestureDetector.onTouchEvent(event)
        }
        return super.onTouchEvent(event)

    }


}