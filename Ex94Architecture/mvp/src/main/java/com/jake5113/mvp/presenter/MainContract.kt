package com.jake5113.mvp.presenter

import android.content.Context
import com.jake5113.mvp.model.Item

// view 와 presenter 역할을 하는 클래스들이 가지고 있어야 할 기능을 정하는 인터페이스
interface MainContract {

    // # view 역할을 하는 클래스가 가져야 할 기능을 기술한 인터페이스
    interface View {
        fun showData(item: Item) // 1) model 의 데이터를 화면에 보여주는 기능 (추상메소드)
        fun getContext(): Context // 2) presenter 에서 사용할 수 있는 Context를 리턴해주는 기능 (추상메소드)
    }

    // # presenter 역할을 하는 클래스가 가져야 할 기능을 기술한 인터페이스
    interface Presenter {

        // 사용자의 이벤트에 따라 처리할 기능 2개 [ view 역할 클래스의 요청에 의해 실행될 메소드 ]
        fun clickSave(name: String, email: String) // 1) save 버튼을 클릭했을 때
        fun clickLoad() // 2) load 버튼을 클릭했을 때


    }
}