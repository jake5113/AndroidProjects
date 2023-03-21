package com.jake5113.ex91kotlinhello

fun main() {


    // 코틀린의 OOP

    // 1. 클래스의 선언 및 객체생성
    // 객체생성이 아주 특이함. new 키워드가 없음
    var obj: MyClass = MyClass()
    obj.show()


    //1.1 별도 파일로 클래스 설계 가능
    var obj2: MyKotlinClass = MyKotlinClass()
    obj2.show()

    // 2. 생성자 [많이 당황스러울 수 있음]
    // 코틀린의 생성자는 2가지 종류가 있음 [주생성자, 보조생성자]

    // 2.1 primary constructor
    var s = Simple() // 주생성자가 자동 호출

    // 2.2 주생성자에 값 전달. 주생성자는 오버로딩 불가!!
    val s2 = Simple2(100, 200, 300)
    s2.show()

    // 2.3 secondary constructor
    val s3 = Simple3()
    val s4 = Simple3(10) // overloading

    // 2.4 주생성자 + 보조생성자 (오버로딩 역할)
    val s5 = Simple4()
    val s6 = Simple4(100) // overloading

    // 2.5 주생성자의 constructor 키워드 생략
    Simple5()


}

class Simple5(){ // constructor 생략 가능
    init {
        println("Simple5 primary constructor")
    }
}




// primary + secondary
class Simple4 constructor() {
    init {
        println("Simple4 primary constructor")
    }

    // 보조생성자 [overloading 역할]
    // 주생성자와 함께 사용할때는 반드시
    // 보조생성자에서 주 생성자의 호출문을 명시적으로 불러야 함.
    // this() 생성자 호출문
    constructor(num: Int) : this() {
        println("Simple4 overloading constructor : $num")
    }
}

// 보조 생성자 - 자바처럼 class 안에 메소드처럼 존재하는 생성자
class Simple3 {

    // 보조생성자
    constructor(){
        println("Simple3 보조 생성자")
    }

    // 보조생성자는 오버로딩 가능 - var 키워드를 붙이는 멤버변수는 사용불가
    constructor(num:Int){
        println("Simple3 보조 생성자 : $num")
    }

}



// 주생성자에 파라미터를 전달하는 클래스 - 주생성자의 파라미터에 var, val 키워드가 있으면.. 파라미터면서 멤버변수임
class Simple2 constructor(num: Int, num2: Int, var num3: Int) {
    // 멤버변수 property
    var num2:Int = 0
    init {
        println("Simple2 primary constructor : $num")
        println("Simple2 primary constructor : $num2")
        // num2 를 다른 지역메소드에서도 사용하고 싶다면
        // 멤버변수로 만들어서 전달해주기
        this.num2 = num2
        println("Simple2 primary constructor : $num3")
    }

    fun show(){
        //println("num : $num") // 생성자의 매개변수는 메소드에서 사용 불가
        println("num2 : $num2") // 멤버변수는 인식가능
        println("num3 : $num3")
    }
}

// 2.1 주생성자 [클래스이름 옆에 작성]
//      주생성자는 별도의 {}가 없음
class Simple constructor() {
    // 초기화 블럭 - 주 생성자의 내용을 작성할 수 있는 영역
    init {
        println("Simple primary constructor!")
        println()
    }
}

class MyClass {
    // 멤버변수 - property : 프로퍼티 - 반드시 초기화 해야함
    var a: Int = 10

    // 멤버함수 - method : 메소드
    fun show() {
        println("show : $a")
    }
}