package com.jake5113.ex06radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg = findViewById(R.id.rg);
        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // RadioButton 중에 선택된 녀석의 글씨를 얻어와서 TextView에 보여주기

                // RadioGroup 에게 체크된 RadioButton의 id 를 얻어오기
                int id = rg.getCheckedRadioButtonId();

                // 체크된 id의 RadioButton 객체를 참조하기
                RadioButton rb = findViewById(id);
                tv.setText(rb.getText().toString());
            }
        });

        // RadioButton의 체크상태가 변경될때 마다 반응하는 리스너는 버튼들에 붙이지 말고
        // RadioGroup에 붙여서 한번에 제어하는 것을 권장함

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 두번째 파라미터 checkedId : 선택된 RadioButton의 id값.
                RadioButton rb = findViewById(checkedId);
                tv.setText(rb.getText().toString());
            }
        });

        // 다른 뷰들의 이벤트 처리도 같은 방식임
        RatingBar ratingBar = findViewById(R.id.rating);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                // 두번째 파라미터 rating : 레이팅 값
                tv.setText("별점 : " + rating);
            }
        });


    } // onCreate method
} // MainActivity class