package com.mrhi2023.ex33toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //툴바를 찾아와서 액션바로 설정
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //액션바의 제목글씨를 보이지 않도록
        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option, menu);

        return super.onCreateOptionsMenu(menu);
    }
}