package com.jake5113.ex92kotlinrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ItemDetailActivity : AppCompatActivity() {

    val iv: ImageView by lazy { findViewById(R.id.iv) }
    // 참조변수의 자료형을 자동추론시키면 find할 때 제네릭 <> 으로 자료형을 알려줘야함
    val tv by lazy { findViewById<TextView>(R.id.tv) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        // 넘어오 Intent 객체가 가져온 Extra 데이터들 받기
        var name: String? = intent.getStringExtra("name")
        var msg: String = intent.getStringExtra("msg") as String
        var imgId: Int = intent.getIntExtra("imgId", R.drawable.newyork)

        // 제목줄에 이름을 표시
        supportActionBar?.title = name

        // 메세지는 TextView 에 표시
        tv.text = msg
        Glide.with(this).load(imgId).into(iv)

        // 전환효과를 줄 뷰에게 별칭 지정하기
        iv.transitionName = "iii"
    }
}