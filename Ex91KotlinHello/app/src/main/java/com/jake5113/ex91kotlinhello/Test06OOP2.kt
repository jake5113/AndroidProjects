package com.jake5113.ex91kotlinhello

fun main() {

    // 2) 코틀린의 상속
    val obj: Second = Second()
    obj.a = 100
    obj.b = 200
    obj.show()


    //3) 업캐스팅, 다운캐스팅
    var f: First = Second()
    f.show() // 실제 참조하는 객체의 show 가 호출
    println()

    // 형변환 연산자 as
    var s: Second = f as Second // down casting
    s.b = 500
    s.show()
    println()
    //-------------------------------------------------

    // 상속 마무리 연습 [Person - Student - Professor - AlbaStudent]
    var p = Person("sam", 20)
    p.show()
    println()

    var stu = Student("robin", 35, "Android")
    stu.show()
    println()

    var pro = Professor("kim", 18, "mobile optimization")
    pro.show()
    println()

    var alba = AlbaStudent("hong", 23, "IOS", "PC management")
    alba.show()
    println()



}// main

// First 클래스를 상속하는 클래스 - 상속문법이 extends --> : 기호
// 상속하는 클래스명 옆에 반드시 생성자 호출문이 명시되어야 함.
class Second : First() {
    // First의 멤버 a, show() 를 보유한 상태
    var b = 20

    // 상속받은 show 메소드의 기능 개선 - override
    // 코틀린은 반드시 오버라이드임을 명시해야만 함
    override fun show() {
        super.show()
        println("b: $b")
    }
}

// 상속해 줄 클래스 - open 키워드가 있어야만 상속 가능한 클래스
open class First{
    var a: Int = 10

    // 기본이 final 메소드여서 override 안됨
    // 허용하려면 open
    open fun show() {
        println("a: $a")
    }
}