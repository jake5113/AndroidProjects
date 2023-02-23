package com.jake5113.ex43activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // 이 액티비티를 실행시켜 준 Intent(택배기사) 소환
        Intent intent = getIntent();
        // 택배기사를 토앻 전달된 Extra 데이터가 있으면 받기
        String name = intent.getStringExtra("name");
        int age = intent.getIntExtra("age", 0);

        getSupportActionBar().setTitle(name);

        tv = findViewById(R.id.tv);
        tv.setText(age + "");
    }
}