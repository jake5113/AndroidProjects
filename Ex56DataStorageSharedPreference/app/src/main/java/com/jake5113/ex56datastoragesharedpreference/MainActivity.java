package com.jake5113.ex56datastoragesharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        tv = findViewById(R.id.tv);

        findViewById(R.id.btn_save).setOnClickListener(view -> clickSave());
        findViewById(R.id.btn_load).setOnClickListener(view -> clickLoad());
    }

    private void clickSave() {
        // 저장할 데이터
        String name = etName.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());

        // SharedPreferences 로 저장하기
        // "Data.xml" 파일에 데이터를 저장하기 위해 객체 얻어오기
        SharedPreferences pref = getSharedPreferences("Data", MODE_PRIVATE);

        // 저장작업 시작
        SharedPreferences.Editor editor = pref.edit(); // 작성자가 리턴됨
        editor.putString("name", name); // ("식별자", 값)
        editor.putInt("age", age);      // ("식별자", 값)

        // 작업 완료
        editor.commit();
    }

    private void clickLoad() {
        SharedPreferences pref = getSharedPreferences("Data", MODE_PRIVATE);

        String name = pref.getString("name", "아무글씨"); // 두번째 파라미터 : default value [저장된 값이 없을때의 값]
        int age = pref.getInt("age", 0);


        tv.setText(name + " : " + age);
    }
}