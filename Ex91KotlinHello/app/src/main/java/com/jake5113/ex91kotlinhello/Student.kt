package com.jake5113.ex91kotlinhello

// 상속해주는 Person 클래스에 이미 name, age 프로퍼티가 존재함
// 그러니 주생성자에서 또 name, age 프로퍼티를 만들면 안됨
// 그러니 그냥 매개변수로 name, age 만 받기
open class Student constructor(name: String, age: Int, var major: String) : Person(name, age) {
    init {
        println("create Student instance")
    }

    // override 키워드가 있는 메소드는 기본 open method.
    override fun show() {
        //super.show()
        println("name : $name   age : $age   major : $major")
    }
}