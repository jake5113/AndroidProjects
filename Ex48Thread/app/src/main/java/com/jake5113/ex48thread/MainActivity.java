package com.jake5113.ex48thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView tv;
    int num;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(view -> {
            // 오래 걸리는 작업 [ex. Network, DB 작업 등..]
            for (int i = 0; i < 20; i++) {
                num++;
                tv.setText(num + "");
                // 스레드를 1초간 잠재우기
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(view -> {
            // 오래 걸리는 작업을 별도의 Thread 에게 수행하도록.
            MyThread t = new MyThread(); // 직원 채용
            t.start(); // 스레드 작업 시작 명령 - Thread클래스의 run() 메소드가 실행됨
        });

    }

    // 오래 걸리는 작업을 수행할 Thread의 작업내역을 설계
    class MyThread extends Thread {
        @Override
        public void run() { // 스레드가 실행할 코드를 작성하는 영역
            for (int i = 0; i < 20; i++) {
                num++;

                // 화면에 num 출력
                //tv.setText(num+""); // error
                // UI 변경 작업은 반드시 Main Thread 만 하도록 강제함.
                // 즉, 별도의 Thread는 UI 작업이 불가능함

                // Main Thread 에게 UI 변경작업 요청
                // 방법1. Handler 객체를 이용하는 방법
                // handler.sendEmptyMessage(0);

                // 방법2. Activity 클래스의 runOnUiThread() 메소드를 이용하는 방법
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 이 영역안에서는 UI작업 가능
                        tv.setText(num + "");
                    }
                });

                // 1초간 잠재우기
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // 별도 Thread가 MainThread에게 UI 변경작업을 요청할 때
    // 그 메세지를 전달하는 역할을 하는 객체 생성
    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            // sendMessage()를 통해 메세지가 전달되면 자동으로 실행되는 영역
            // 이 영역에서는 UI작업이 가능함
            tv.setText(num+"");
        }
    };


}