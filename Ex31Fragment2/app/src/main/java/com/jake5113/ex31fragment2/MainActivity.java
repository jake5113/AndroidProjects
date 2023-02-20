package com.jake5113.ex31fragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 프레그먼트 관리자 소환
        fragmentManager = getSupportFragmentManager();

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitTransaction")
            @Override
            public void onClick(View v) {
                // 아이디가 container_fragment 인 뷰 그룹에 MyFragment를 붙이기

                // 프레그먼트의 동적(add, remove, replace) 작업 시작
                FragmentTransaction tran = fragmentManager.beginTransaction();

                // 프레그먼트 동적 추가
                tran.add(R.id.container_fragment, new MyFragment());

                // 뒤로가기 버튼 눌렀을때 프레그먼트 추가 이전 상태로 돌아가려면..
                tran.addToBackStack(null);+

                // 작업완료!!
                tran.commit();

            }
        });

    }
}