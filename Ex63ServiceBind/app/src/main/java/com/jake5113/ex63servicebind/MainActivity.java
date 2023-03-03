package com.jake5113.ex63servicebind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyMusicService musicService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_play).setOnClickListener(v -> clickPlay());
        findViewById(R.id.btn_pause).setOnClickListener(v -> clickPause());
        findViewById(R.id.btn_stop).setOnClickListener(v -> clickStop());

    }

    // 액티비티가 화면에 보여질 때 자동으로 발동하는 콜백메소드
    @Override
    protected void onResume() {
        super.onResume();

        if (musicService == null) {
            // MusicService 를 실행하고 연결하기
            Intent intent = new Intent(this, MyMusicService.class);
            startService(intent); //Service 객체가 없으면 create 하고 onStartCommand()를 호출함.

            // Service 객체와 연결(bind)
            bindService(intent, connection, 0);
        }
    }

    // MyMusicService 와 연결하는 터널 객체
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Toast.makeText(MainActivity.this, "binded..", Toast.LENGTH_SHORT).show();

            // 두번째 파라미터 : iBinder - 터널을 통해 넘어온 객체
            MyMusicService.MyBinder binder = (MyMusicService.MyBinder) iBinder;
            musicService = binder.getServiceObject();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    private void clickPlay() {
        if (musicService != null) musicService.playMusic();
    }

    private void clickPause() {
        if (musicService != null) musicService.pauseMusic();
    }

    private void clickStop() {
        if (musicService != null) {
            musicService.stopMusic();
            unbindService(connection); //서비스와 연결 종료
            musicService = null;
        }

        //완전하게 서비스를 종료하기 위해
        Intent intent = new Intent(this, MyMusicService.class);
        stopService(intent);

        //액티비티도 종료
        finish();
    }
}