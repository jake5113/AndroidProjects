package com.jake5113.ex97databinding

import androidx.databinding.ObservableField

// MVVM 패턴의 View 에서 사용할 데이터 (model) 을 연결해주는 ViewModel 역할의 클래스 정의
class MyDataViewModel {
    // 이미지뷰에서 보여줄 이미지 source URL (문자열 경로)
    val img: ObservableField<String> = ObservableField("https://cdn.pixabay.com/photo/2023/05/21/07/47/horse-8008038__340.jpg")

    // 리사이클러뷰가 사용할 대량의 데이터
    val items : ObservableField<MutableList<String>> = ObservableField(mutableListOf()) // 빈 MutableList 객체 생성 초기화

}