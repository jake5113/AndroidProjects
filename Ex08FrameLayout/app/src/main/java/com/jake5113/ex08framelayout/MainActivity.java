package com.jake5113.ex08framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btn01, btn02, btn03;
    LinearLayout layout01, layout02, layout03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn01 = findViewById(R.id.btn01);
        btn02 = findViewById(R.id.btn02);
        btn03 = findViewById(R.id.btn03);

        layout01 = findViewById(R.id.layout01);
        layout02 = findViewById(R.id.layout02);
        layout03 = findViewById(R.id.layout03);

        btn01.setOnClickListener(listener);
        btn02.setOnClickListener(listener);
        btn03.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) { // 파라미터 view : 클릭된 버튼 참조변수

            layout01.setVisibility(View.GONE);
            layout02.setVisibility(View.GONE);
            layout03.setVisibility(View.GONE);

            int id = view.getId();
            if (id == btn01.getId()) layout01.setVisibility(View.VISIBLE);
            else if (id == btn02.getId()) layout02.setVisibility(View.VISIBLE);
            else if (id == R.id.btn03) layout03.setVisibility(View.VISIBLE);
        }
    };


}