package com.mrhi2023.ex98databindingbindingadapter

import androidx.databinding.ObservableField
import java.util.Date

class MyViewModel {

    //1) 이미지의 URL 변수 [ 관찰 가능한 타입 - ObservableXXX ]
    var img: ObservableField<String> = ObservableField("https://cdn.pixabay.com/photo/2015/03/09/18/34/beach-666122_1280.jpg")

    //1.1) 버튼 클릭 이벤트 콜백에서 호출하는 메소드
    fun changeImage(){
        img.set("https://cdn.vox-cdn.com/thumbor/05ouNatmtxFDAZFB_lUp_a1UK20=/0x0:800x333/1220x813/filters:focal(336x103:464x231):gifv():no_upscale()/cdn.vox-cdn.com/uploads/chorus_image/image/55278743/gatsby.1497548146.gif")
    }


    //2) 리사이클러뷰가 보여줄 대량의 데이터 컬렉션
    val items: ObservableField<MutableList<Item>> = ObservableField(mutableListOf( Item("sam","Hello"), Item("robin","Nice"))) //2개의 아이템을 가진 리스트 객체로 초기화

    //2.1) 버튼 클릭 이벤트 콜백 에서 호출하는 메소드
    fun addItem(){
        //원래는 서버에서 새로운 데이터를 읽어오는 코드...
        //테스트 목적으로 그냥 Item 추가
//        val list= items.get()
//        list?.add(0, Item("NEW", Date().toString()))
//        items.set(list) // 같은 객체(리스트)를 다시 설정하면 화면갱신 안됨

        val list: MutableList<Item> = mutableListOf()
        list.add(Item("NEW", Date().toString()))
        list.addAll(items.get()!!)
        items.set(list)
    }

}

// ###############################
// ObservableXXX 는 몇가지 단점이 있음.
// 1. 새로 set 하는 객체가 변경되지 않으면 화면갱신이 안됨.
// 2. 액티비티나 프래그먼트의 라이프사이클을 고려하지 않고 데이터 변경에 반응함.. 화면이 종료되는 상황에서도 화면갱신을 시도함..
// 이런 단점을 개선하기 위해 Jetpack 라이브러리로 배포된 LiveData 라는 녀석이 등장함.