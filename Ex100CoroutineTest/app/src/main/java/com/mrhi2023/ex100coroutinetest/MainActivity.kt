package com.mrhi2023.ex100coroutinetest

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.mrhi2023.ex100coroutinetest.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class MainActivity : AppCompatActivity() {

    val binding:ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Coroutine[코루틴] - 경량 스레드 : 스레드를 멈추지 않고 비동기 처리- 하나의 스레드안에 여러개의 코루틴 실행
        // 스레드가 요리사라면  멀티스레드는 여러 요리사가 화구(cpu)를 번갈아 사용하는 기술. 다른 요리사가 사용중에는 기존 요리사는 동작을 멈춤
        // 코루틴은 하나의 요리사(스레드)가 파스타를 만들면서 스테이크를 굽는 형식임. 즉, 팬이 2개인 것임. 자리를 비켜가면서 멈추는 행동이 없어서 좀 더 빠르게 동시 작업이 가능함.

        // 코루틴을 구동하는 2개의 범위(Scope)가 존재함
        //1. GlobalScope    : 앱 전체의 생명주기와 함께 관리되는 범위
        //2. CoroutineScope : 버튼 클릭같은 특정 이벤트 순간에 해야할 Job을 위해 실행되는 범위 [ ex. network통신, DB CRUD, 특정연산수행 등 ]

        // 실습 1) GlobalScope 코드 연습
        binding.btn.setOnClickListener {
            // 코루틴 없이 오래걸리는 작업 실행해보기
//            for( n in 0..9 ){
//                Log.d("TAG", "n: $n")
//                Thread.sleep(500)
//            }

            // 비동기 작업으로 위 작업을 수행 - 코루틴을 사용해보기
            GlobalScope.launch {
                for( n in 0..9 ){
                    Log.d("TAG", "n: $n  -  ${Thread.currentThread().name} ")
                    delay(500)
                }
            }

            Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show()
        }

        //실습2) CoroutineScope 비동기 실행
        // CoroutineScope는 GlobalScope와 다르게 해당 작업을 어떤 스레드에게 보낼지 결정하는 Dispatcher[디스패처]를 지정해야 함.
        // Dispatcher의 종류
        // 1] Dispatchers.Default - 기본 스레드풀의 스레드를 사용 [ cpu작업이 많은 연산작업에 적합]
        // 2) Dispatchers.IO      - DB나 네트워크 IO 스레드를 사용 [ 파일입출력, 서버작업에 적합 ]
        // 3] Dispatchers.Main    - Main 스레드를 사용   [ UI작업등에 적합 ]
        // 4] Dispatchers.Unconfined - 조금 특별한 디스패처 [ 해당 코루틴을 호출하는 스레드에서 실행 ]

        binding.btn2.setOnClickListener {
            // Dispatchers.Default 사용
            CoroutineScope(Dispatchers.Default).launch {
                for(n in 100..110){
                    Log.d("TAG","n: $n  - ${Thread.currentThread().name}")

                    //binding.tv.text= "n: $n  - ${Thread.currentThread().name} " // 체크 필요! UI변경 불가해야함.
                    delay(500)
                }
            }

            Toast.makeText(this, "bbb", Toast.LENGTH_SHORT).show()
        }

        binding.btn3.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                for (n in 200..210){
                    binding.tv.text="n: $n - ${Thread.currentThread().name} " //UI작업 가능
                    delay(500)
                }
            }

            Toast.makeText(this, "ccc", Toast.LENGTH_SHORT).show()
        }

        binding.btn4.setOnClickListener {
            // Main에서 서버작업 시도...
            CoroutineScope(Dispatchers.Main).launch {

                //네트워크 이미지 불러오기 -- 에러!! MainThread는 네트워크 작업 불가능

                val url= URL("https://cdn.pixabay.com/photo/2016/03/04/19/36/beach-1236581_1280.jpg")
                val bm:Bitmap= BitmapFactory.decodeStream( url.openStream() )

                binding.iv.setImageBitmap(bm)
            }
        }

        binding.btn5.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val url= URL("https://cdn.pixabay.com/photo/2016/03/04/19/36/beach-1236581_1280.jpg")
                val bm:Bitmap= BitmapFactory.decodeStream( url.openStream() )

                //binding.iv.setImageBitmap(bm)//UI변경
                withContext(Dispatchers.Main){
                    binding.iv.setImageBitmap(bm)
                }
            }
        }


        binding.btn6.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {

                //작업 1
                launch {
                    for(n1 in 1000 .. 1010){
                        Log.d("TAG", "n1: $n1")
                        delay(500)
                    }
                }

                //작업 2
                launch {
                    for(n1 in 2000 .. 2010){
                        Log.d("TAG", "n1: $n1")
                        delay(500)
                    }
                }

            }
        }

        binding.btn7.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {

                //작업 1
                launch {
                    for(n1 in 1000 .. 1010){
                        Log.d("TAG", "n1: $n1")
                        delay(500)
                    }
                }.join() // 작업1이 끝날때 까지 다른 코루틴은 실행을 대기함

                //작업 2
                launch{
                    for(n1 in 2000 .. 2010){
                        Log.d("TAG", "n1: $n1")
                        delay(500)
                    }
                }

            }


        }


        binding.btn8.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {
                someTask()
            }
        }


        // 코루틴의 제어
        var job: Job?= null
        binding.btn9.setOnClickListener {
            job= CoroutineScope(Dispatchers.Default).launch {
                for(n in 300 .. 310){
                    Log.d("TAG", "n: $n")
                    delay(500)
                }
            }
        }

        binding.btn10.setOnClickListener {
            job?.cancel()
        }



    }//onCreate method..

    //코루틴 스코프 범위 밖에서 코루틴의 기능을 사용할때 함수를 suspend 함수로 만들면 해결할 수 있음.
    suspend fun someTask(){
        for(n in 1000 .. 1010){
            Log.i("TAG", "someTask : $n")
            delay(500)
        }
    }

}