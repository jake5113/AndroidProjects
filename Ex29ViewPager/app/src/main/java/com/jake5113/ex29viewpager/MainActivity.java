package com.jake5113.ex29viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> imgIds = new ArrayList<>();

    ViewPager2 pager;
    MyAdapter adapter;

    Button btnPrev, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 대량의 데이터 추가 [실무에서는 DB나 서버에서 데이터를 읽어옴]
        imgIds.add(R.drawable.bg_one01);
        imgIds.add(R.drawable.bg_one02);
        imgIds.add(R.drawable.bg_one03);
        imgIds.add(R.drawable.bg_one04);
        imgIds.add(R.drawable.bg_one05);
        imgIds.add(R.drawable.bg_one06);
        imgIds.add(R.drawable.bg_one07);
        imgIds.add(R.drawable.bg_one08);
        imgIds.add(R.drawable.bg_one09);
        imgIds.add(R.drawable.bg_one10);

        pager = findViewById(R.id.pager);
        adapter = new MyAdapter(this, imgIds);
        pager.setAdapter(adapter);

        btnPrev = findViewById(R.id.btn_prev);
        btnNext = findViewById(R.id.btn_next);

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 뷰페이저의 현재 위치 인덱스 번호를 얻어오기
                int position = pager.getCurrentItem();
                // 현재위치 이전번호로 지정
                pager.setCurrentItem(position - 1, true);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = pager.getCurrentItem();
                pager.setCurrentItem(position + 1, false);
            }
        });
    }
}