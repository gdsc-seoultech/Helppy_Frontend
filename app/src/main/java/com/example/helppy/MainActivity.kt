package com.example.helppy

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.example.helppy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bae.setOnClickListener { btnOnClick("bae") }
        binding.yogi.setOnClickListener { btnOnClick("yogi") }
        binding.eats.setOnClickListener { btnOnClick( "eats") }
        binding.ddaeng.setOnClickListener { btnOnClick("ddaeng") }
        binding.coupang.setOnClickListener { btnOnClick("coupang") }
        binding.gmarket.setOnClickListener { btnOnClick("gmarket") }


    }


    fun btnOnClick(name: String) {

        val packageList = mapOf(        // 플레이스토어 패키지 주소
            "bae" to "com.sampleapp&hl=ko&gl=US",
            "yogi" to "com.fineapp.yogiyo&hl=ko&gl=US",
            "eats" to "com.coupang.mobile.eats&hl=ko&gl=US",
            "ddaeng" to "com.shinhan.o2o.store&hl=ko&gl=US",
            "coupang" to "com.coupang.mobile&hl=ko&gl=US",
            "gmarket" to "com.ebay.kr.gmarket&hl=ko&gl=US")

        val intentAdress = packageManager.getLaunchIntentForPackage(packageList[name].toString()) // 인텐트에 패키지 주소 저장

        try {
            startActivity(intentAdress) // 앱 실행
        } catch (e: Exception) {  // 만약 실행이 안된다면 (앱이 없다면)
            Log.d("helppy", "no application")
            val intentPlayStore = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageList[name].toString())) // 설치 링크를 인텐트에 담아
            startActivity(intentPlayStore) // 플레이스토어로 이동
        }
    }
}