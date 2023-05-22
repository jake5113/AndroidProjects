package com.jake5113.ex94architecturepattern

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jake5113.ex94architecturepattern.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    Architecture pattern 없이 그냥 작성하는 Flat Design 방식
//    1) 장점 : 구조가 간단하여 구현하기 쉽고 하나의 문서에 대부분의 기능 코드와 UI 코드가 있어서 전체 기능이 한 눈에 들어옴.
//    2) 단점 : - Activity, Fragment 에 모든 기능 코드가 있어서 규모가 커지면 파일 안에 너무 많은 코드가 있어서 비대해짐. 유지보수 어려움.
//             - 똑같은 데이터를 제어하는 코드를 다른 화면에서 사용하게 되더라도 같은 코드를 또 작성해야 함. 재사용이 어려움.

//    그래서 등장한 Architecture Pattern
//    작성 코드의 역할에 따라 구분하여 작성하는 방법을 규격화 한 패턴
//    대표적인 패턴 : MVC, MVP, MVVM
//    차례대로 실습.. 새로운 모듈을 만들어서 실습 및 비교

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // # 화면 작업
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // # 클릭 이벤트 처리
        binding.btnSave.setOnClickListener { clickedSave() }
        binding.btnLoad.setOnClickListener { clickedLoad() }
    }

    // # Data를 제어 (저장/읽기/변경/삭제 : CRUD) 작업을 하는 비지니스 로직 코드 기능 메소드 2개..[ex. 네트워크통신, DB작업 등. ]
    private fun clickedSave() {
        val pref: SharedPreferences = getSharedPreferences("data", MODE_PRIVATE) // 안써도 data.xml
        pref.edit().apply() {
            putString("name", binding.etName.text.toString())
            putString("email", binding.etEmail.text.toString())
            commit()
        }
    }

    private fun clickedLoad() {
        val pref: SharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
        var name: String? = pref.getString("name", "")
        var email: String = pref.getString("email", "")!!

        binding.tv.text = "$name : $email"
    }

}