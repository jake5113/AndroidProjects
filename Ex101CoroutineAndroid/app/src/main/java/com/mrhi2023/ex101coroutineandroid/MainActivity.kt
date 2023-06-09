package com.mrhi2023.ex101coroutineandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 코틀린 언어에서 지원하는 코루틴은 2종류 : GlobalScope, CoroutineScope
        // 안드로이드는 액티비티 or 프레그먼트의 라이프사이클이 존재함. 이에 함께 반응하는 코루틴이 존재함
        // LifecycleScope  /  ViewModelScope -- 별도의 라이브러리 추가 필요!!

        findViewById<Button>(R.id.btn).setOnClickListener { clickBtn1() }
        findViewById<Button>(R.id.btn2).setOnClickListener { clickBtn2() }
        findViewById<Button>(R.id.btn3).setOnClickListener { clickBtn3() }
    }

    private fun clickBtn1(){
        CoroutineScope(Dispatchers.Default).launch {
            for(n in 0 .. 20){
                Log.d("TAG", "coroutine scope : $n")
                delay(500)
            }
        }
    }

    private fun clickBtn2(){
        //android의 라이프사이클에 같이 제어되는 lifecycleScope
        // this.이 생략.. [ onCreate() ~~ onDestroy() 까지의 액티비티 라이프사이클 Owner ]
        lifecycleScope.launch {
            for(n in 100 .. 120){
                Log.d("TAG", "lifecycle scope : $n")
                delay(500)
            }
        }
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        finish()
    }


    fun clickBtn3(){
        // [ onResume() ~~ onPause() 동안에만 코루틴 동작 .. onPause()되면 자동 일시정지. 다시 onResume()되면 자동으로 이어서 실행
        lifecycleScope.launchWhenResumed {
           loopTask()
        }
    }

    suspend fun loopTask(){
        for( n in 300 .. 320){
            Log.d("TAG", "lifecycle scope when resume : $n")
            delay(500)
        }
    }

}