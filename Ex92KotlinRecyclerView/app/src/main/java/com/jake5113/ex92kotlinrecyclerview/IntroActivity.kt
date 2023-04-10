package com.jake5113.ex92kotlinrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button

class IntroActivity : AppCompatActivity() {

    // 코틀린은 멤버변수(프로퍼티)를 초기화하지 않으면 에러
    var btn: Button? = null // nullable variable

    // 늦은 초기화 문법
    lateinit var btn2: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        // Button 객체 참조하기
        btn = findViewById(R.id.btn)
        // nullable 변수는 null 일 수도 있어서 안전하게 멤버에 접근하는 연산자를 사용해야 함.
        btn?.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                val intent: Intent = Intent(this@IntroActivity, MainActivity::class.java)
                startActivity(intent)
            }
        })

        // btn2 참조하기
        btn2 = findViewById(R.id.btn_exit)
        //리스너설정을 익명클래스 말고 간결하게 람다함수로
        //btn2.setOnClickListener ({v-> finish()})

        //파라미터가 1개라면 생략가능 [자동 it 키워드로 파라미터명 지정됨]
        /*btn2.setOnClickListener({
            finish()
        })*/

        // 람다함수를 더 축약하여 SAM(Single Abstract Method) 변환
        btn2.setOnClickListener {
            finish()
        }












    }
}