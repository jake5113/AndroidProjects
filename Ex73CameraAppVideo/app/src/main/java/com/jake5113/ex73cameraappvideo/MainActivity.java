package com.jake5113.ex73cameraappvideo;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaSession2Service;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        vv = findViewById(R.id.vv);
        findViewById(R.id.btn).setOnClickListener(v -> clickBtn());
    }

    private void clickBtn() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        launcher.launch(intent);
    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_CANCELED) return;

        Uri uri = result.getData().getData();
        tv.setText(uri.toString());
        vv.setVideoURI(uri);
        // 비디오가 로딩하는 시간이 소요됨. 그래서 로딩완료 리스너로 처리함
        vv.setOnPreparedListener(mp -> {
            vv.start();
        });

    });
}