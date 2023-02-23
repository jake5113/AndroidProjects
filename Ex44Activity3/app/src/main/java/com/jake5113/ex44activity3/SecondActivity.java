package com.jake5113.ex44activity3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    EditText etName, etAge;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(view -> {
            // EditText에 입력한 데이터들을 결과로 설정
            String name = etName.getText().toString();
            int age = Integer.parseInt(etAge.getText().toString());

            // 다시 돌아갈 택배기사(Intent) 에게 결과 데이터를 넣기
            Intent intent = getIntent();
            intent.putExtra("name", name);
            intent.putExtra("age", age);

            setResult(RESULT_OK, intent);
            finish();
        });
    }
}