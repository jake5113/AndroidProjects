package com.jake5113.ex62service;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(v -> {
            // 백그라운드 작업을 Service 컴포넌트를 이용
            Intent intent = new Intent(this, MyService.class);

            // Oreo 버전부터 "Foreground Service" 도입 - 퍼미션 필요함
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) startForegroundService(intent);
            else startService(intent);

            startService(intent);
        });

        findViewById(R.id.btn_stop).setOnClickListener(v -> {
            Intent intent = new Intent(this, MyService.class);
            stopService(intent);
        });

        // 알림에 대한 퍼미션 체크 및 요청

        int checkResult = checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS);
        if (checkResult == PackageManager.PERMISSION_DENIED) {
            // 퍼미션 요청결과를 받아주는 대행사 객체를 활용
            permissionResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
        }
    }

    // 퍼미션요청 결과를 받아주는 대행사 객체 생성 및 등록
    ActivityResultLauncher<String> permissionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            if (result) Toast.makeText(MainActivity.this, "알림 허용", Toast.LENGTH_SHORT).show();
            else Toast.makeText(MainActivity.this, "알림, 서비스 불가", Toast.LENGTH_SHORT).show();
        }
    });

    @Override
    public void onBackPressed() {
        finish();
    }
}