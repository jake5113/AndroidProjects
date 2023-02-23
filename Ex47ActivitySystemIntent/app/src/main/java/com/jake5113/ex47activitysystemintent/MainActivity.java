package com.jake5113.ex47activitysystemintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_DIAL);

            // 미리 전화번호까지 전달하려면
            intent.setData(Uri.parse("tel:01012345678"));

            startActivity(intent);
        });

        findViewById(R.id.btn2).setOnClickListener(view -> {
            // SMS 문자 화면 (메세지 앱)
            Intent intent = new Intent(Intent.ACTION_SENDTO); // 생성자로 action 설정 가능

            intent.setData(Uri.parse("smsto:010456789, 01022224444"));

            // 문자내용을 프로그래밍
            intent.putExtra("sms_body", "Hello...Nice to meet you");

            startActivity(intent);
        });

        findViewById(R.id.btn3).setOnClickListener(view -> {
            // 웹페이지 보는 화면 [크롬브라우저 앱]
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.naver.com"));// 액션, Data 한번에
            startActivity(intent);
        });

        findViewById(R.id.btn4).setOnClickListener(view -> {
            // 카메라 앱
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
        });

    }
}