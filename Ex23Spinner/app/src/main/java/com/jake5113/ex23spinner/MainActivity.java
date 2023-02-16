package com.jake5113.ex23spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        //adapter = new ArrayAdapter();// 대량의 데이터가 자바에 있을때만 사용 가능 xml에 있을때 불가능
        adapter = ArrayAdapter.createFromResource(this, R.array.city, R.layout.spinner_item);

        // 드롭다운되는 아이템들의 뷰모양을 다르게 주고 싶다면.
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        spinner.setAdapter(adapter);

        // 스피너의 드롭다운되는 뷰의 위치를 조정
        spinner.setDropDownVerticalOffset(160);

        // 스피너의 아이템이 선택되었을때..
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] city = getResources().getStringArray(R.array.city);
                Toast.makeText(MainActivity.this, city[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}