package com.jake5113.ex61backgroundtaskbythread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyThread myThread; // 스레드 참조변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(v -> {
            // 백그라운드에서 반복작업을 수행하는 별도의 Thread 객체 생성 및 실행
            if (myThread != null) return;

            myThread = new MyThread();
            myThread.start();

        });

        findViewById(R.id.btn_stop).setOnClickListener(v -> {
            if (myThread != null) {
                // 스레드는 run메소드가 종료되는 멈춤. 1회성 객체라서 다시 실행불가
                // while문 때문에 run 메소드가 종료되지 않고 있음.
                // 그러니 멈추려면, while문의 조건값 isRun을 false로 변경
                myThread.isRun = false; // (안좋은 코드!) stopThread() 를 만드는 것을 권장!
                myThread = null;
            } else {
                Toast.makeText(this, "Thread 객체를 참조하고 있지 않습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 디바이스의 뒤로가기 버튼을 클릭했을때 반응하는 콜백메소드


    @Override
    public void onBackPressed() {
        //super.onBackPressed(); // 기본 MainActivity는 종료되지 않기에..
        finish();
    }

    // 백그라운드 동작을 하는 별도 Thread 클래스 설계
    class MyThread extends Thread {

        boolean isRun = true;

        @Override
        public void run() {

            while (isRun) {
                // 별도 Thread는 UI 작업 불가
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "백그라운드 작업...", Toast.LENGTH_SHORT).show();
                    Log.i("EX61", "백그라운드 작업...");
                });

                // 5초간 대기
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }// while..
        }// run method..
    }// MyThread class..

}