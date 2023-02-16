package com.jake5113.ex04imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // 액티비티가 보여주는 뷰들의 참조변수는 가급적 멤버변수에 선언
    ImageView iv;
    Button btnAus, btnBel, btnCan, btnKor;
    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv);
        btnAus = findViewById(R.id.btn01);
        btnBel = findViewById(R.id.btn02);
        btnCan = findViewById(R.id.btn03);
        btnKor = findViewById(R.id.btn04);

        btnAus.setOnClickListener(listener);
        btnBel.setOnClickListener(listener);
        btnCan.setOnClickListener(listener);
        btnKor.setOnClickListener(listener);

        // 이미지뷰를 클랙했을때 그림을 차례대로 변경
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.setImageResource(R.drawable.flag_australia + num);
                num++;
                if (num > 12) num=0;
            }
        });

    } // onCreateMethod..

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) { // 파라미터 view : 클릭을 한 버튼을 참조하는 참조변수

            // 어떤 뷰(버튼)를 클릭하였는지 알아내기
            int id = view.getId();

            // id에 따라 해당 버튼의 기능을 구현 -  이미지뷰의 이미지를 변경해보기
            if(id == R.id.btn01) iv.setImageResource(R.drawable.flag_australia);
            else if (id == R.id.btn02) iv.setImageResource(R.drawable.flag_belgium);
            else if (id == R.id.btn03) iv.setImageResource(R.drawable.flag_canada);
            else if (id == R.id.btn04) iv.setImageResource(R.drawable.flag_korea);
        }
    };


} // MainActivity class...