package com.jake5113.ex91kotlinhello

fun main() {

    // 안드로이드에서 많이 사용하는 기술
    // 1) 이너클래스
    // 2) 인터페이스 및 익명클래스
    // 3) static 키워드를 대체한 companion object [동반객체]
    // 4) 늦은 초기화 - lateinit, by lazy

    // 1. 이너클래스
    val obj = AAA()
    // inner class 는 외부에서 직접 객체생성 불가!!
    // inner class 객체는 아웃터클래스만 만들 수 있음
    //val obj2:AAA.BBB = AAA.BBB() //error
    val obj2: AAA.BBB = obj.getBBBinstance()
    obj2.show()
    println()

    // 2. 인터페이스
    // 인터페이스는 객체생성 불가 - 기능설계가 안되어 있어서
    //var c:Clickable = Clickable() //error
    var c: Clickable = Test() // up casting
    c.onClick()
    println()

    // 2.1 익명클래스
    // 다른 기능을 하는 또 다른 Clickable 이 필요
    // 또 다시 Test 같은 새로운 class 를 명명하는 것이 짜증
    // 객체생성하면서 인터페이스를 그 자리에서 구현하는 이름없는 클래스 - 익명클래스
    // 익명클래스 객체를 만드는 키워드 object
    var c2: Clickable = object : Clickable {
        override fun onClick() {
            println("apple...")
        }
    }
    c2.onClick()
    println()

    // 3. 동반객체 companion object : 정적멤버 static 키워드의 대체 문법 static X
    //    클래스(설계도면)에 붙어 있는 객체 같은 녀석
    //    별도의 객체생성없이 클래스명만으로 접근가능한 녀석
    println(Sample.title)
    Sample.show()
    println()
    //---------------------------------------------------------------------------------

    // 4. 늦은초기화
    // 4.1 lateinit  [var 변수만 사용가능]
    var h: Hello = Hello()
    //println(h.name) // Exception 발생
    h.onCreate()
    println(h.name)


    // 4.2 by lazy   [val 변수만 사용가능]
    println(h.add) // add 변수는 객체 생성할 때 초기화
    println(h.address) // 이 순간 초기화
    println(h.tel) // 이 순간 초기화
}// main

class Hello {
    // 4.1) lateinit
    //var name:String // ERROR - 초기화 없으면

    // 만약 초기화를 나중에 하고 싶다면..
    lateinit var name: String

    fun onCreate() {
        name = "sam" // 이때 초기화가 됨
    }

    // lateinit 사용 특징
    // 1) nullable 변수는 lateinit 불가
    //lateinit var title: String? // error

    // 2) 기본자료형(8개)은 사용불가
    //lateinit var age:Int // error

    // 3) val 에는 사용불가
    //lateinit val address:String //error

    // 4.2) by lazy
    //val address:String // error - 초기화 필요
    val address: String by lazy { "seoul" } // 이 변수가 처음 사용될 때 초기화
    val add: String = "busan" // 이 순간 초기화

    val tel: String by lazy {
        println("늦은 초기화...")
        "마지막 값"
    }

    // by lazy 의 특징
    // 1) 기본형 자료형도 가능함
    val age: Int by lazy { 20 }

    // 2) nullable 도 가능함
    val message : String by lazy { "Hello" }
    val message2 : String? by lazy { null }

    // 3) 조건값으로 값 대입도 가능함
    val message3 : String by lazy {
        if (age < 20) "미성년자"
        else "성인"
    }

    // 4) var 에는 사용불가
    //var sss:String by lazy{"Nice"} // error

}


class Sample {
    var a: Int = 10 // 인스턴스변수는 객체 생성할때만 사용가능

    companion object {
        // 이 안에 있는 멤버들은 이미 객체화 되었기에 그냥 사용가능
        // 단, sample 클래스에 동반되었기에 클래스명이 요구됨

        var title: String = "Hello"
        fun show() {
            println("동반객체의 show()")
        }
    }
}


// 인터페이스의 추상메소드들을 구현하는 별도의 클래스를 설계하고
// 이 클래스를 객체로 만들어서 사용해야 함.
// 인터페이스를 구현할때는 상속과 다르게 인터페이스명 옆에 () 생성자 호출문 없음.
class Test : Clickable {
    override fun onClick() {
        println("clicked!")
    }
}


// interface 는 특별할 것이 없음 - 추상메소드만 가진 class
interface Clickable {
    // abstract method - 이름만 있는 메소드
    //abstract fun onClick() - abstract 생략 가능
    fun onClick()

}


class AAA {
    var a: Int = 0

    fun show() {
        println("AAA클래스의 show : $a")
    }

    // inner class 객체를 생성하여 리턴해주는 기능메소드
    //fun getBBBinstance():BBB = BBB()
    fun getBBBinstance(): BBB {
        return BBB()
    }

    // 이너클래스 - inner 키워드가 있어야 진정한 이너클래스가 됨
    inner class BBB {
        fun show() {
            // 이너클래스의 장점 - 아웃터의 멤버를 내 것인양
            a = 100
            println("아웃터 클래스의 멤버 a : $a")

            // 아웃터의 show 메소드를 호출해보기
            this@AAA.show()
        }

    } ////////////
}