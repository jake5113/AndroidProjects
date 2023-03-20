package com.jake5113.ex91kotlinhello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

// Kotlin 에서 클래스 상속 키워드는 ":"이며 상속하는 클래스명 옆에 주생성자를 호출하는 () 가 필수임.
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 대략적인 코틀린의 코딩 방식 살펴보기 - 자바와의 차이를 기반으로 소개

        // 변수는 var 키워드 사용
        var btn: Button = findViewById(R.id.btn)

        // 버튼에 클릭리스너 설정하기 - 자바의 람다식과 비슷한 SAM 변환 제공
        btn.setOnClickListener{
            clickBtn()
        }
    }

    // Kotlin 에서의 메소드(함수)는 fun 키워드 사용
    private fun clickBtn() {
        // 변수 선언시에 자료형을 생략할 수 있음.
        var tv = findViewById<TextView>(R.id.tv)
        //tv.setText("Nice")

        //Kotlin은 setXXX() 메소드를 권장하지 않고. 멤버변수에 값 대입을 선호함
        tv.text = "Nice to meet you"

    }

    // Override 메소드가 Java 에서는 @Override 어노테이션을 사용했지만
    // Kt 에서는 메소드 앞에 override 키워드 삽입
    // 오버라이드 메소드 앞에 override 명시적으로 키워드가 없으면 에러
    override fun onResume() {
        super.onResume()
        // 코틀린에서는 소문자로 toast를 써야 자동완성기능이 발동함.
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()

    }
}