package com.jake5113.ex58notification;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(v -> {

            // Android 13버전(API 33) 부터 알림에 대한 퍼미션이 추가됨
            // 이 앱이 알림에 대한 퍼미션을 허용한 상태인지 체크
            int checkResult = ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS);
            if (checkResult == PackageManager.PERMISSION_DENIED) {
                // 알림 허용을 요청하는 다이얼로그를 보이기
                //requestPermissions(); // 예전방식

                // 퍼미션 요청결과를 받아주는 대행사 객체를 이용함.
                permissionResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
                return;
            }

            // 알림(Notification)을 관리하는 관리자 객체 소환
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            // 알림객체를 생성해주는 건축가(Builder)객체 생성
            NotificationCompat.Builder builder = null;

            // 빌더를 생성하는 문법이 26버전(Oreo)부터 변경되었음.
            // 알림채널 이라는 개념이 도입되었음.
            // 그래서 26버전 이상의 폰에서는 알림채널객체를 생성해야 하고
            // 그 이전 버전에서는 알림채널객체 생성없이 빌더를 만들어야 함.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // 알림 채널 객체 생성
                NotificationChannel channel = new NotificationChannel("ch01", "Ex58 channel", NotificationManager.IMPORTANCE_HIGH);

                // 알림 사운드, 진동 (채널이 있을때)
                //Uri uri = RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION);
                Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.s_goodjob);
                channel.setSound(uri, new AudioAttributes.Builder().build());

                // 진동은 사용자 정적 퍼미션 요구됨
                channel.setVibrationPattern(new long[] {0,2000,1000,3000});

                // 매니저에게 채널을 등록하기 전에 사운드 작업해야 함.
                notificationManager.createNotificationChannel(channel);

                builder = new NotificationCompat.Builder(this, "ch01");
            } else {
                builder = new NotificationCompat.Builder(this, ""); // 채널이 없으니 아이디도 그냥 빈 문자열로.

                Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.s_goodjob);
                builder.setSound(uri);
            }

            // 빌더에게 알림에 관련된 설정들

            // 상태 표시줄에 보이는 아이콘
            builder.setSmallIcon(R.drawable.ic_noti);

            // 상태바를 드래그하여 아래로 내리면 보이는 알림창(확장 상태바)의 설정
            builder.setContentTitle("Title");
            builder.setContentText("message...");

            Resources res = getResources();
            Bitmap bm = BitmapFactory.decodeResource(res, R.drawable.ms_02);
            builder.setLargeIcon(bm);

            // 알림창을 클릭했을때 새로운 화면(Activity)가 실행되도록
            Intent intent = new Intent(this, SecondActivity.class);
            // Intent 객체에게 바로 실행하지 말고 잠시 보류해 달라고
            // 보류중인 인텐트 (pendingIntent) 객체로 생성
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 10, intent, PendingIntent.FLAG_IMMUTABLE);
            builder.setContentIntent(pendingIntent);

            // 알림창을 클릭하여 화면이 변경되면
            // 상태표시줄의 작은 아이콘이미지가 없어지도록..
            builder.setAutoCancel(true);

            // 요즘들어 보이는 알림창 꾸미기..
            NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
            style.bigPicture(BitmapFactory.decodeResource(res, R.drawable.penguins));
            builder.setStyle(style);

            // 다른 스타일의 종류도 많음.. 개발자 사이트를 통해 추후 확인

            // 알림창의 클릭 액션에 의해 실행될 화면이 여러개 일때 사용하는 기능
            builder.addAction(R.drawable.ic_noti, "setting", pendingIntent);
            builder.addAction(R.drawable.ic_noti, "information", pendingIntent);


            // 건축가에게 알림객체 생성을 요청
            Notification notification = builder.build();

            // 알림 매니저에게 알림을 보이도록 요청
            notificationManager.notify(100, notification);


        });
    }

    // 퍼미션 요청 결과를 받아오는 작업을 대행해주는 객체 생성 및 등록
    ActivityResultLauncher<String> permissionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            if (result) Toast.makeText(MainActivity.this, "알림 허용", Toast.LENGTH_SHORT).show();
            else Toast.makeText(MainActivity.this, "알림 거부", Toast.LENGTH_SHORT).show();
        }
    });
}