package com.jake5113.ex42activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // 제목줄 제목 글씨 변경
        getSupportActionBar().setTitle("Third");

        // 제목 왼쪽에 돌아가는 버튼 [업버튼] 보이도록
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // [업버튼]이 클릭됐을때 자동으로 발동하는 콜백메소드


    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }
}
