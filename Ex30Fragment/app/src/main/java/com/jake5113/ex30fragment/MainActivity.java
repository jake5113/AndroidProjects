package com.jake5113.ex30fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button btn, btn2;
    MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("Hello Android");
            }
        });
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MyFragment 객체를 찾아서 참조하기
                // Fragment를 관리하는 관리자 객체를 소환
                FragmentManager manager = getSupportFragmentManager();
                myFragment = (MyFragment) manager.findFragmentById(R.id.frag_my);

                myFragment.tv.setText("초밥 먹으러 가야지");
            }
        });
    }
}