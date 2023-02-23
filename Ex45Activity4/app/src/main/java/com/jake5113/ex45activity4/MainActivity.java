package com.jake5113.ex45activity4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(view -> {
            // SecondActivity.class 명칭 없이 실행해보기 - 묵시적 인텐트
            Intent intent = new Intent();
            //intent.setAction("aaaa");
            intent.setAction("bbbb");

            startActivity(intent);
        });
    }
}