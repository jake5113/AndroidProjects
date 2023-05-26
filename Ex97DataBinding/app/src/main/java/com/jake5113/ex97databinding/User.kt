package com.jake5113.ex97databinding

import android.database.Observable
import android.text.Editable
import android.text.TextWatcher
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
    fun increaseAge(v: View) {
        age.set(age.get() + 1)
    }

    // 좋아요 true/false 변경하는 기능 메소드 - 콜백용 메소드가 아닌 그냥 일반 메소드
    // 이 메소드를 xml 버튼의 onClick 속성으로 지정한 익명콜백함수에서 대신 호출해 줄 것임
    fun toggleFav() { // 파라미터가 없음!!
        favor.set(!favor.get())
    }

    // 체크상태가 변경되는 것에 반응하는 콜백메소드 - 파라미터 중요!!
    fun changeFav(v: CompoundButton, isChecked: Boolean) {
        Toast.makeText(v.context, "$isChecked", Toast.LENGTH_SHORT).show()
        // 체크상태값을 관리하는 fav 변수값도 변경
        favor.set(isChecked)
    }

    // EditText의 글씨 변화값을 가지고 있을 관찰 가능한 변수
    val message: ObservableField<String> = ObservableField("message")


    // EditText의 글씨 변화 이벤트에 반응하는 콜백메소드 - 파라미터 중요
    fun onTextChange(s: CharSequence?, start: Int, before: Int, count: Int) {
        message.set(s.toString())
    }

    // EditText 에 글씨를 입력하고 버튼을 클릭하여 텍스트뷰에 보여주기
    private var inputValue: String = ""
    val value: ObservableField<String> = ObservableField(inputValue)

    // EditText의 글씨 변경 이벤트 콜백 메소드에 의해 호출될 일반 메소드
    fun changeInputValue(s: CharSequence) {
        inputValue = s.toString()
    }

    // 작성완료버튼 클릭 콜백메소드에 의해 호출될 일반 메소드
    fun clickBtn(){
        value.set(inputValue)
    }

}