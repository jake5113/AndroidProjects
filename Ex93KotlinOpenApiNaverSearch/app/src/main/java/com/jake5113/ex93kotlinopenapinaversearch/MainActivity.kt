package com.jake5113.ex93kotlinopenapinaversearch

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson
import com.jake5113.ex93kotlinopenapinaversearch.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn.setOnClickListener { searchData() }

    }

    private fun searchData() {

        // 소프트 키보드 없애기
        val imm : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        // Naver (쇼핑)검색 OPEN API 사용해보기

        // 네트워크 작업을 대신 작성해주는 라이브러리 활용 : Retrofit
        // 1. 레트로핏 생성
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // 2. 레트로핏이 해줄 작업에 대한 요구 명세( 인터페이스 설계 및 추상메소드 정의 )
        // RetrofitService.kt 설계

        // 3. 레트로핏 서비스 객체를 생성
        val retrofitService: RetrofitService = retrofit.create(RetrofitService::class.java)

        // 4. 원하는 작업 요청하여 네트워크 작업 실행 객체 리턴받기
        val call: Call<NaverSearchApiResponse> =
            retrofitService.searchData(binding.et.text.toString())

        // 5. 네트워크 작업 시작
        call.enqueue(object : Callback<NaverSearchApiResponse> {
            override fun onResponse(
                call: Call<NaverSearchApiResponse>,
                response: Response<NaverSearchApiResponse>
            ) {
                val naverResponse: NaverSearchApiResponse? = response.body()

                // 응답받은 객체의 items 리스트를 리사이클러뷰에 보이기.
                binding.recycler.adapter = MyAdapter(this@MainActivity, naverResponse!!.items)

                //Toast.makeText(this@MainActivity, "아이템 개수 : ${naverResponse?.items?.size}", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<NaverSearchApiResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "실패! : ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })


        /*        // 4. 원하는 작업요청하여 네트워크 작업 실행 객체 리턴받기
                val call: Call<String> = retrofitService.searchDataByString(
                    "alsP6H9I23jfN3YuEmkr",
                    "CRAYHNBXmn",
                    binding.et.text.toString()
                )

                // 5. 작업 시작
                call.enqueue(object : Callback<String>{
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        var s: String? = response.body()
                        AlertDialog.Builder(this@MainActivity).setMessage(s).show()
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "error", Toast.LENGTH_SHORT).show()
                    }
                })*/
    }
}