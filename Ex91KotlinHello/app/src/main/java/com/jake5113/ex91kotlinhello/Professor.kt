package com.jake5113.ex91kotlinhello

// 보조 생성자를 이용하여 상속받아보기.. 본인이 보조생성자를 이용하면
// 상속해주는 클래스의 주생성자를 호출 ()를 쓰지 않음
class Professor : Person {

    // 교수만의 고유 멤버변수
    var subject: String? = null

    // 보조생성자를 사용하면 부모클래스의 생성자를 이곳에서 호출함
    constructor(name: String, age: Int, subject: String) : super(name, age) {
        this.subject = subject
        println("create Professor instance")
    }

    override fun show() {
        //super.show()
        println("name : $name    age : $age   subject : $subject")
    }

}