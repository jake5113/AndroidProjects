package com.jake5113.ex91kotlinhello

import android.app.AlertDialog
import android.content.Context

fun main() {
    // scope function : apply, let, run, also

    // 어떤 객체의 여러개의 멤버를 사용해야 할때
    val member = Member()

    member.name = "sam"
    member.age = 20
    member.address = "seoul"
    member.show()

    // 멤버 4개를 사용할 때 객체명.xxx 라고 쓰는게 은근 번거롭고 실수의 여지도 많음
    // 이를 위해 등장한 scope 함수
    val member2 = Member()
    member2.apply {
        // 이 영역안에서는 this 키워드가 member2 객체임
        this.name = "robin"
        // this는 생략 가능
        age = 15
        address = "busan"
        show() // 메소드 호출도 가능
    }

    // 위처럼 영역을 묶었기에 참조변수명을 잘못 기입하는 실수를 줄일 수도 있고,
    // 개발자가 볼때 member2 에 대한 설정을 하나의 영역에 묶어서 가독성이 개선됨.

    // scope function 의 크게 2가지 분류
    // 1) 영역안에서 this 키워드로 본인을 참조하는 scope function : apply, run
    // 2) 영역안에서 it 키워드로 본인을 참조하는 scope function : also, let -- 마치 람다식처럼
    val member3 = Member()
    member3.also {
        it.name = "hong"
        // it 키워드는 생략 불가
        it.age = 30
        it.address = "paris"
        it.show()
    }

    // it 키워드를 다른 키워드로 변경가능
    member3.let { m ->
        m.name = "lee"
        m.age = 35
        m.address = "tokyo"
        m.show()
    }

    // apply 와 run 의 차이는 리턴값이 다름
    //  also 와 let 의 차이도 리턴값이 다름
    // 안드로이드에 사용하는 모습 샘플

//    val builder: AlertDialog.Builder = AlertDialog.Builder(this)
//    builder.setTitle("aaa")
//
//
//    val builder2: AlertDialog.Builder = AlertDialog.Builder(this)
//
//    // run function 의 마지막 실행문의 결과가 리턴값
//    val dialog2 = builder2.run {
//        setTitle("aaa")
//        setMessage("blabla")
//        setPositiveButton("OK", null)
//        setNegativeButton("NO", null)
//        create()// 마지막 실행문의 결과가 리턴됨
//    }
//    dialog2.show()
//
//
//    // apply function 의 리턴값은 this임
//    val builder3 = builder2.apply {
//        setTitle("aaa")
//        setMessage("blabla")
//        setPositiveButton("OK", null)
//        setNegativeButton("NO", null)
//        create()
//    }

}

class Member {
    var name: String? = null
    var age: Int? = null
    var address: String? = null

    fun show() {
        println("$name  $age  $address")
    }
}