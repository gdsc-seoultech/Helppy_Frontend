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
        if (event != null) {
            gestureDetector.onTouchEvent(event)
        }
        return super.onTouchEvent(event)

    }


}