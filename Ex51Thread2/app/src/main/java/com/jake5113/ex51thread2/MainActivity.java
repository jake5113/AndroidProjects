package com.jake5113.ex51thread2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    MyThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 화면에 보이지 않더라도 별도 Thread는 백그라운드에서 동작하고 있다는 것을 확인해보기
        findViewById(R.id.btn).setOnClickListener(view -> {
            thread = new MyThread();
            thread.start();
        });
    }//onCreate method..

    // 이너클래스
    class MyThread extends Thread {

        boolean isRun = true;

        @Override
        public void run() {

            // 5초마다 현재 시간을 Toast 로 보이도록
            while (isRun) {
                // 현재시간을 문자열로
                Date now = new Date();
                String s = now.toString();

                // Toast 로 현재시간(s) 보여주기
                // Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show(); //ERROR

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

                        //Log 기록 남기기
                        Log.i("EX51", s);
                    }
                });

                // 5초간 잠재우기
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            Log.i("EX51", "스레드 종료!");
        }
    }

    // 액티비티가 메모리에서 없어질때(종료) 자동으로 실행되는 콜백 메소드
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // AndroidStudio 의 Logcat 탭에 기록(log) 남기기
        Log.i("EX51", "onDestroy");

        // 스레드의 작업을 그만하도록 해야할 의무가 있음!!!!
        thread.isRun = false;
        thread = null;

        Log.i("EX51", "끝");
    }

    // 디바이스의 [뒤로가기버튼]을 클릭했을때 반응하는 콜백메소드


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        // MainActivity를 뒤로가기를 눌러도 안꺼짐..[단지 안보일뿐..]
        // 강제로 Activity를 종료
        finish();
    }
}//MainActivity class..