package com.jake5113.ex91kotlinhello

// 주 생성자의 constructor 키워드 생략
class AlbaStudent(name:String, age:Int, major:String, var task:String) : Student(name, age, major){

    init {
        println("create AlbaStudent instance")
    }

    override fun show() {
        //super.show()
        println("name : $name    age : $age   major : $major   task : $task")
    }

}