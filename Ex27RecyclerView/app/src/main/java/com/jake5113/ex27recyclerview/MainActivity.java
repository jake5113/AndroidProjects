package com.jake5113.ex27recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // 대량의 데이터
    ArrayList<Item> items = new ArrayList<>();
    RecyclerView recyclerView;
    MyAdapter adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 대량의 데이터 추가
        items.add(new Item("sam", "Hello android"));
        items.add(new Item("robin", "Hello ios"));
        items.add(new Item("Hong", "Nice android"));
        items.add(new Item("Park", "Nice ios"));
        items.add(new Item("Lee", "Hello React"));
        items.add(new Item("Jake", "Hello World"));
        items.add(new Item("홍길동", "안녕하세요"));
        items.add(new Item("sam", "Hello android"));
        items.add(new Item("robin", "Hello ios"));
        items.add(new Item("Hong", "Nice android"));
        items.add(new Item("Park", "Nice ios"));
        items.add(new Item("Lee", "Hello React"));
        items.add(new Item("Jake", "Hello World"));
        items.add(new Item("홍길동", "안녕하세요"));

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, items);
        recyclerView.setAdapter(adapter);

        // 리사이클러뷰의 아이템뷰 1개를 클릭했을때 반응하는 리스너처리가 없음.
        // 그래서 처리하려면 아이템뷰 1개를 만드는 MyAdapter.java 에서 onClick 처리해야함

    }
}