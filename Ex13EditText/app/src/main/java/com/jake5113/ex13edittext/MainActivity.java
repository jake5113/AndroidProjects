package com.jake5113.ex13edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et01, et02, et03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et01 = findViewById(R.id.et01);
        et02 = findViewById(R.id.et02);
        et03 = findViewById(R.id.et03);

        // EditText의 글씨가 변경될때 마다 감시하는 리스너객체 생성 및 추가
        et01.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 글자수가 3글자인지 확인
                if(s.length()>=3) et02.requestFocus();
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et02.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=4) et03.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}