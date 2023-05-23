package com.jake5113.mvp.presenter

import android.content.Context
import com.jake5113.mvp.model.Item
import com.jake5113.mvp.model.ItemModel
import com.jake5113.mvp.view.MainActivity
import java.util.Objects

// presenter 라면 가져야 할 기능을 기술한 인터페이스를 구현하여 실제 기능을 작성하는 클래스
class MainPresenter : MainContract.Presenter {

    // presenter 는 view 와 model 을 연결해야 하기에 각각의 참조변수를 멤버로 보유

    // 1. view 역할을 수행하는 클래스는 MainContract.View 인터페이스를 구현하고 있기에 특정 Activity/Fragment를 직접 자료형으로 참조하고 있지 않음. 약한 결합
    var view: MainContract.View? = null

    // 2. model 역할을 수행하는 클래스 참조변수
    var model: ItemModel? = null

    // presenter 가 연결한 2개의 참조변수를 생성 및 전달 받는 메소드 정의
    fun initial(view: MainContract.View) {
        this.view = view
        model = ItemModel(view.getContext())
    }

    // view 의 save 버튼 클릭 이벤트를 대신 처리해주는 기능메소드
    override fun clickSave(name: String, email: String) {
        // model 에게 저장 요청
        model?.saveData(name, email)
    }

    override fun clickLoad() {
        // model 에게 data 요청
        var item: Item? = model?.loadData()

        // view 에게 데이터 출력 요청
        item?.let {
            view?.showData(it)
        }
    }
}