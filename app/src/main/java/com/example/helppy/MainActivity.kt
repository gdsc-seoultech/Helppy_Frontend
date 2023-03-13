package com.example.helppy

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.helppy.databinding.ActivityMainBinding
import com.example.helppy.utils.MyAccessibility


class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 앱 실행 시 접근성 다이얼로그 띄우기
        alertDialog()

        // 스크린리더 on
        MyAccessibility()

        // 각 앱 누를 경우 앱으로 이동
        binding.bae.setOnClickListener { btnOnClick("bae") }
        binding.yogi.setOnClickListener { btnOnClick("yogi") }
        binding.eats.setOnClickListener { btnOnClick( "eats") }
        binding.ddaeng.setOnClickListener { btnOnClick("ddaeng") }
        binding.coupang.setOnClickListener { btnOnClick("coupang") }
        binding.gmarket.setOnClickListener { btnOnClick("gmarket") }

        // TODO: 3. 앱으로 이동 시 백그라운드 활동


        // TODO: 4. 앱으로 다시 돌아왔을 때 intent로 설정 화면으로 이동
        onResume()

    }

//


    private fun btnOnClick(name: String) {

        val packageList = mapOf(        // 플레이스토어 패키지 주소
            "bae" to "com.sampleapp",
            "yogi" to "com.fineapp.yogiyo&hl=ko&gl=US",
            "eats" to "com.coupang.mobile.eats&hl=ko&gl=US",
            "ddaeng" to "com.shinhan.o2o.store&hl=ko&gl=US",
            "coupang" to "com.coupang.mobile&hl=ko&gl=US",
            "gmarket" to "com.ebay.kr.gmarket&hl=ko&gl=US")

        val intentAddress = packageManager.getLaunchIntentForPackage(packageList[name].toString()) // 인텐트에 패키지 주소 저장

        if (intentAddress != null) {
            startActivity(intentAddress) // 앱 실행
        } else {
            try {
                Log.d("open-app", packageList[name].toString())
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$" + packageList[name].toString()))) // 앱 없을 경우 플레이스토어 실행
            } catch (e: ActivityNotFoundException) { // 플레이스토어 실행 오류 시 플레이스토어 웹페이지로 이동
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageList[name].toString())))
            }
        }

    }

    private fun alertDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setMessage("[아임리더]를 사용하시겠습니까? \n[설정]을 누르고 [TalkBack]을 비활성화해주세요.")
        alertDialog.setPositiveButton("확인") {dialog, which ->
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivity(intent)
        }
        alertDialog.setNegativeButton("취소") { dialog, which ->
            Toast.makeText(this, "[아임리더] 사용을 위해서는 [TalkBack] 비활성화가 필요합니다. 다시 시도해주세요.", Toast.LENGTH_LONG).show()
            this.finish()
        }
        alertDialog.show()
    }
    override fun onResume() {
        super.onResume()

        val intent = intent
        val data = intent.data

        if (data != null && data.scheme == "myapp") {
            // 외부앱에서 돌아왔을 때, myapp 스키마로 전달된 데이터가 있는 경우에만 다이얼로그를 띄웁니다.
            alertDialogEnd()
        }
    }

    private fun alertDialogEnd() {

        val alertDialog_end = AlertDialog.Builder(this)
        alertDialog_end.setMessage("[아임리더]를 종료하시겠습니까? \n[설정]을 누르고 [TalkBack]을 활성화해주세요.")
        alertDialog_end.setPositiveButton("확인") {dialog, which ->
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivity(intent)
        }
        alertDialog_end.setNegativeButton("취소") { dialog, which ->
            Toast.makeText(this, "[TalkBack]을 비활성화한 상태로 앱을 종료합니다.", Toast.LENGTH_LONG).show()
            this.finish() // 앱 종료
        }
        alertDialog_end.show()
    }

}