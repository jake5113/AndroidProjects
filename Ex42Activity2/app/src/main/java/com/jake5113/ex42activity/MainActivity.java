package com.jake5113.ex42activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 두번째 화면(SecondActivity)으로 이동

                // SecondActivity를 실행시켜 줄 택배기사 객체 생성
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                // 택배기사를 통해 새로운 액티비티를 실행
                startActivity(intent);

                // 현재 액티비티를 종료
                finish();
            }
        });
        btn2 = findViewById(R.id.btn2);
        // 람다식 표기 - 익명클래스의 축약표현 문법
        btn2.setOnClickListener(view -> {
            // ThirdActivity를 실행시켜 줄 택배기사 객체 생성
            Intent intent = new Intent(this, ThirdActivity.class);
            startActivity(intent);
        });
    }
}