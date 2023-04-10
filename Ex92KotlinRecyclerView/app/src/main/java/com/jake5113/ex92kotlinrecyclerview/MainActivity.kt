package com.jake5113.ex92kotlinrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL

class MainActivity : AppCompatActivity() {

    // 뷰 참조변수는 보통 참조값이 변경되지 않기에 var 보다는 val 로 만들어보기
    // 늦은 초기화
    val recycler: RecyclerView by lazy { findViewById(R.id.recycler) }

    // 대량의 데이터들
    var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 대량의 데이터들 추가 [ 테스트 목적 ]
        items.add(Item("Sam", "Hello Kotlin", R.drawable.newyork))
        items.add(Item("Robin", "Hello Android", R.drawable.paris))
        items.add(Item("Tom", "Hello IOS", R.drawable.sydney))
        items.add(Item("Lee", "Hello Kotlin", R.drawable.newyork))
        items.add(Item("Park", "Hello Android", R.drawable.paris))
        items.add(Item("Jake", "Hello IOS", R.drawable.sydney))
        items.add(Item("Paul", "Hello Kotlin", R.drawable.newyork))
        items.add(Item("Rose", "Hello Android", R.drawable.paris))
        items.add(Item("Yeah", "Hello IOS", R.drawable.sydney))
        items.add(Item("Jack", "Hello Kotlin", R.drawable.newyork))
        items.add(Item("Tim", "Hello Android", R.drawable.paris))
        items.add(Item("Sally", "Hello IOS", R.drawable.sydney))

        // 리사이클러뷰에 아답터를 설정하기
        recycler.adapter = MyAdapter(this, items)

        // 리사이클러뷰에 레이아웃매니저를 설정하기
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onResume() {
        super.onResume()

        // 보통 이곳에서 데이터들을 갱신하는 작업들이 이루어 짐
        recycler.adapter?.notifyDataSetChanged()
        recycler.adapter!!.notifyDataSetChanged()
    }

    // 옵션메뉴를 만드는 작업을 하는 콜백메소드
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // MenuInflater를 get하는 작업필요없이 액티비티에 이미 menuInflater 객체가 멤버로 존재함
        menuInflater.inflate(R.menu.option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // 옵션메뉴 아이템을 선택하면 자동으로 발동하는 콜백메소드
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_aa -> Toast.makeText(this, "aa", Toast.LENGTH_SHORT).show()
            R.id.menu_bb -> {
                Toast.makeText(this, "bb", Toast.LENGTH_SHORT).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}