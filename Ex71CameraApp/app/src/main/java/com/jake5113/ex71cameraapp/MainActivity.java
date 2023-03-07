package com.jake5113.ex71cameraapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv);
        findViewById(R.id.btn).setOnClickListener(v -> clickBtn());
    }

    void clickBtn() {
        //카메라 앱 실행
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        resultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            //사진 촬영 파일의 Uri 얻어오기
            Uri uri = result.getData().getData();
            if (uri == null) Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();

            // 프로그램을 통해 실행한 카메라앱에서 촬영한 사진은 디바이스에  파일로 저장되지 않음
            // 촬영한 사진의 섬네일 정보를 Bitmap 객체로 만들어서 Extra 데이터로 전달해줌
            Intent intent = result.getData();
            Bundle bundle = intent.getExtras();
            Bitmap bm = (Bitmap) bundle.get("data");
            iv.setImageBitmap(bm);

            // 개발자가 파일로 저장되길 원한다면.
            // 인텐트로 카메라 앱을 실행할때 추가 데이터를 설정해야 함.
            // 다음 예제로..
        }
    });

}