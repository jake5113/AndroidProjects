package com.jake5113.ex91kotlinhello

fun main() {

    // 3. 연산자 특이점 ///////
    // 숫자타입들간의 산술연산은 자동형변환이 수행됨 [작은것 --> 큰것]
    println(10 + 3.14)

    // 숫자타입이 아닌 자료형과는 자동형변환 안됨
    //println(10 + true) //error
    //println(10 + 'A') //error

    // 자료형을 체크하는 연산자 is
    var n = 10
    if (n is Int) {
        println("n은 Int형 변수")
    }

    // is 연산자로 nullable 과 non-nullable 을 구분하지 않음.
    var n2 = "Hello"
    if (n2 is String) println("n2변수는 String")
    if (n2 is String?) println("n2변수는 String?")

    // !is 도 있음
    if (n2 !is String) println("n2변수는 String이 아니다")

    // 다른 자료형은 is로 체크하면 문법에러
    // if (n2 is Int){} //ERROR

    // 그럼 is는 큰 의미가 없어보이지만 Any 타입에 대한 식별로 많이 사용됨
    var obj: Any
    obj = 10
    if (obj is Int) println("${obj}는 Int 입니다")
    if (obj is Double) println("${obj}는 Double 입니다")

    // 자바의 instanceof 같은 기능으로 사용함

    class Person {
        var name: String = "Sam"
        var age = 20
    }

    var p = Person() // Person 객체 생성
    //if (p is Person)
    println(p.name + "  " + p.age)

    // is 연산자의 특이 기능 - is를 통해 어떤 객체인지 판별되었다면 참인 영역안에서는 그 객체로 참조변수를 인식함.
    var obj2: Any
    obj2 = Person()
    // obj2 의 자료형이 Any 타입이기에 멤버변수 name, age 가 자동리스트업 되지 않음.
    if (obj2 is Person) {
        // 참인 영역안에서는 obj2 를 Person 참조변수로 인식함
        println("${obj2.name} - ${obj2.age}")
    }

    // 비트연산자가 없음. [&, |, ~, ^]
    // 대신에 그 연산기능을 가진 메소드가 존재함
    //println(7 | 3) //error
    println(7.or(3))
    println(7 or 3) // 마치 연산자처럼 or 메소드 표기 가능
    println()
    //////////////////////////////////////////////////////////////////////////////////

    // 4. 조건문 : if, when [switch 문법이 없음]

    // 4.1 if 표현식 - if 문이나 else 문의 마지막 실행문이 변수에 대입될 수 있음.
    var ss: String
    if (10 > 5) ss = "Hello"
    else ss = "Nice"

    var sss: String = if (10 > 5) {
        "Hello"
        println("aaa")
        "Good" // 마지막 값이 들어감
    } else {
        "Nice"
    }
    // 이런 특징 때문에 if 문을 코틀린에서는 제어문이라는 대신에 [if표현식]이라고 부름
    // 그래서 코틀린에서는 삼항연산자가 없음 - if 표현식으로 대체
    //var str:String = (10>5)?"aaa":"bbb" //error
    var str: String = if (10 > 5) "aaa" else "bbb"
    println(str)
    println()

    // 4.2 switch 문법이 없어지고 대신 when 문법이 대체함
    var h: Any? = null

    // switch(h){} //error - switch 문법 없음

    h = 40
    when (h) {
        10 -> println("aaa")
        20 -> println("bbb")
        30 -> println("ccc")
        // 자료형이 다른 경우를 배치해도 됨
        "Hello" -> println("Hello 출력")
        true -> println("true")

        // 변수가 있어도 됨
        n -> println("n변수와 값이 같음")

        // 2개 이상의 조건을 묶을 수도 있음.
        30, 40 -> println("30 or 40")

        // switch 문의 default 의 역할
        else -> { // 실행문이 여러줄이면 .. {}
            println("ddd")
            println("end")
        }
    }

    // when 도 if 문 처럼 표현식이라서 결과를 변수에 저장
    h = 30
    var result = when (h) {
        10 -> "aaa"
        20 -> "bbb"
        else -> {
            println("else")
            "bad"
            "sss"
            //println("abbb")
        }
    }
    println(result)

    // when 에 is 키워드 연산자 사용 가능
    h = 50
    when (h) {
        is Int -> println("Int 타입")
        is String -> println("String 타입")
        else -> println("else")
    }

    // when 을 특정 수식으로 제어하고 싶을때
    // 주의!! when 사용 문법이 약간 다름
    h = 95
    when {
        //h >= 90 && h <= 100 -> println("A학점")
        h in 90..100 -> println("A학점")
        h >= 80 -> println("B학점")
        h >= 70 -> println("C학점")
        h >= 60 -> println("D학점")
        else -> println("F학점")
    }
////////////////////////////////////////////////////////////////////////////////////////////////////

    // 5. 반복문 : while, for
    // while 문은 다른 것이 없음

    // for 문은 작성하는 문법이 완전히 다름
    //for (var i = 0; i<5; i++){} // 이런 복잡한 문법 없음

    // 0부터 5까지 6번 실행되는 반복문
    for(i in 0..5){
        println(i)
    }
    println()

    // 제어용  변수인 i를 다른 이름으로 바꿔도 됨
    for (a in 3..10) {
        println(a)
    }
    println()

    // 제어변수 앞에 var 키워드를 추가하면 에러
    //for (var t in 0..5){} //error

    // 마지막 숫자 전까지 하려면  .. 대신에 until
    for (i in 0 until 10) {
        println(i)
    }
    println()

    // 2씩 증가 (step)
    for (i in 0..10 step 2) {
        println(i)
    }
    println()

    // 값의 감소 (downTo)
    for (i in 10 downTo 0 step 2) {
        println(i)
    }

    for(n in 0..5){
        if(n==3) break
        print("$n   ")
    }
    println()

    for(y in 0..5){
        print("$y : ")
        for(x in 0..10){
            if(x==6) break
            print("$x   ")
        }
        println()
    }
    println()

    //@Label로 break위치 선택
    KKK@ for(y in 0..5){
        print("$y : ")
        for(x in 0..10){
            if(x==6) break@KKK
            print("$x   ")
        }
        println()
    }
}