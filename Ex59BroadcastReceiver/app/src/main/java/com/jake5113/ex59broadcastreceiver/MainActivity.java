package com.jake5113.ex59broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(v->clickBtn());
    }
    private void clickBtn() {
        // Broadcast 보내기
        // 원래 방송은 안드로이드라는 운영체제가 보내는 것임. 이 예제에서는 실습목적으로

        // 방송 : 실제 전파를 쏜다는 느낌이 아니라.. Intent 라는 객체가 모든 앱에게 전달되는 모습.
        // 즉, 방송을 보낸다는 것은 Intent를 모든 앱에게 보내는 것.

        // 1. 명시적 인텐트 - 특정 리시버에게만 보내는 방송 (본인 앱 안에 있는 리시버만 수신)
//        Intent intent = new Intent(this, MyReceiver.class);
//        sendBroadcast(intent);

        // 2. 암시적 인텐트 - 디바이스에 설치된 모든 앱안에 있는 리시버가 듣는 방송 Intent
        // 안드로이드 OS 에서 보내는 방송은 대부분 암시적 인텐트 임.
        // Oreo 버전부터 암시적 인텐트 방송은 System 만 할 수 있도록 제한함.
        // 그럼에도 꼭 암시적 인텐트를 보내고 싶다면.. 리시버를 매니페스트가 아닌
        // 자바에서 동적으로 등록하면 테스트 가능함.
        Intent intent = new Intent();
        intent.setAction("aaab"); // 방송을 구별하는 식별자 역할 [action]

        sendBroadcast(intent);
    }

    MyReceiver myReceiver;

    // 액티비티가 화면에 보여질 때 자동으로 실행되는 콜백메소드
    @Override
    protected void onResume() {
        super.onResume();

        // 리시버 등록
        myReceiver = new MyReceiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction("aaab");

        // 리시버를 등록하면서 필터도 설정
        registerReceiver(myReceiver, filter);
    }

    // 액티비티가 화면에서 안보이기 시작할 때 자동으로 실행되는 콜백메소드
    @Override
    protected void onPause() {
        super.onPause();

        // 리시버를 제거
        unregisterReceiver(myReceiver);
    }
}