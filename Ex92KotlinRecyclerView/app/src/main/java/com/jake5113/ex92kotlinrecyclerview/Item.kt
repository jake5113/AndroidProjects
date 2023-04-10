package com.jake5113.ex92kotlinrecyclerview

// 매개변수면서 멤버변수

// data class : 데이터만 저장하는 목적의 클래스
// 일반클래스와 다르게 자동으로 equals() 할 때 객체 주소를 비교하지 않고
// 멤버값들을 비교해주도록 설계된 클래스 / 주생성자의 멤버만 비교함.
data class Item constructor(var name: String, var msg: String, var imgId: Int)