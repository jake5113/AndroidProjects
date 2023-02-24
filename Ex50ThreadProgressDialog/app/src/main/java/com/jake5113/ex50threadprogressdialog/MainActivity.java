package com.jake5113.ex50threadprogressdialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(view -> clickBtn());
        findViewById(R.id.btn2).setOnClickListener(view -> clickBtn2());
    }

    ProgressDialog dialog;
    int gauge = 0;

    void clickBtn() {
        // wheel type progress dialog
        if (dialog != null) return;

        dialog = new ProgressDialog(this);
        dialog.setTitle("wheel dialog");
        dialog.setMessage("downloading");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        // 테스트 목적으로 3초 뒤에 다이얼로그를 종료
        handler.sendEmptyMessageDelayed(0, 3000);
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            dialog.dismiss();
            dialog = null;
        }
    };

    void clickBtn2() {
        if (dialog != null) return;

        dialog = new ProgressDialog(this);
        dialog.setTitle("bar dialog");
        dialog.setMessage("다운로드 중...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        dialog.setMax(100);

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        // 막대바의 Gauge 값을 증가시키는 별도의 Thread
        new Thread() {
            @Override
            public void run() {
                gauge = 0;

                while (gauge < 100) {
                    gauge++;
                    dialog.setProgress(gauge);

                    // 0.05초 대기
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                dialog.dismiss();
                dialog = null;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "다운로드 완료", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();
    }
}