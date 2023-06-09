package com.mrhi2023.ex98databindingbindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

//기존 뷰들에 없는 새로운 xml 속성을 연결하는 기능 메소드를 가지는 객체 [ 보통 static 메소드를 가진 class로 사용 ]
//object 키워드 - 싱글톤 패턴으로 객체를 만들어주는 키워드
object MyBindingAdapter {

    //1) 이미지뷰에 새로운 xml 속성을 만들기 - [ 속성명 : imageUrl ]
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(view:ImageView, url:String){ //메소드명은 개발자 마음대로.. 파라미터가 중요함 [ 뷰타입, 속성값 ]
        Glide.with(view.context).load(url).into(view)
    }



    //2) 리사이클러뷰에 리스트를 설정할 수 있는 새로운 xml 속성을 만들기 - [ 속성명 : itemList ]
    @BindingAdapter("itemList")
    @JvmStatic
    fun setItemList(view:RecyclerView, items:Any){ //컬랙션 타입은 사용 불가능- 그래서 Any 타입
        view.adapter= RecyclerItemAdapter(view.context, items as List<Item>)
    }

}