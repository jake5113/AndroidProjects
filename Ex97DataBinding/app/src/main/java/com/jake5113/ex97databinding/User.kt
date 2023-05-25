package com.jake5113.ex97databinding

import android.database.Observable
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

class User {

    // 값 변경이 관찰되는 멤버변수 ObservableXXX : primitive type && List or Map && Reference type --> ObservableField<>
    var name: ObservableField<String> = ObservableField<String>("")
    var age: ObservableInt = ObservableInt(0) // 초기값
    var favor: ObservableBoolean = ObservableBoolean()

    constructor(name: String, age: Int, favor: Boolean = true) {
        this.name.set(name)
        this.age.set(age)
        this.favor.set(favor)
    }

    // change name 버튼 클릭 callback method
    fun changeName(view: View) {
        name.set("robin")
    }

    // age 변수값을 1 증가 시키는 기능 콜백 메소드
    fun increaseAge(v:View){
        age.set(age.get() + 1)
    }

    // 좋아요 true/false 변경하는 기능 메소드 - 콜백용 메소드가 아닌 그냥 일반 메소드
    // 이 메소드를 xml 버튼의 onClick 속성으로 지정한 익명콜백함수에서 대신 호출해 줄 것임
    fun toggleFav(){ // 파라미터가 없음!!
        favor.set(!favor.get())
    }

    // 체크상태가 변경되는 것에 반응하는 콜백메소드 - 파라미터 중요!!
    fun changeFav(v:CompoundButton, isChecked:Boolean){
        Toast.makeText(v.context, "$isChecked", Toast.LENGTH_SHORT).show()
        // 체크상태값을 관리하는 fav 변수값도 변경
        favor.set(isChecked)
    }


}