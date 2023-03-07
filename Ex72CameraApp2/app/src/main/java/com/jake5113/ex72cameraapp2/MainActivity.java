package com.jake5113.ex72cameraapp2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv);
        tv = findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(v -> clickBtn());
    }

    // 촬영한 이미지가 저장될 파일의 URI[콘텐츠 경로 - DB resource 경로]
    Uri imgUri = null;

    private void clickBtn() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 개발자가 저장되길 원하는 위치의 파일 경로 URI를 만들어주는 기능 호출
        createImgUri();

        // 촬영한 이미지를 파일로 저장하도록..추가 데이터로 [저장될 이미지의 Uri] 설정
        if (imgUri != null) intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);

        resultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            // 카메라앱이 촬영한 이미지를 EXTRA_OUTPUT 으로 지정한 imgUri에 저장했을 것임.

            Glide.with(this).load(imgUri).into(iv);
        }
    });

    // 이미지의 경로 Uri 를 생성하는 기능메소드 정의
    private void createImgUri() {
        // 저장될 파일의 경로를 지정
        // 외부저장소에 저장하고자 함.
        // 1. 외부저장소의 앱 전용 영역
        // 2. 외부저장소의 미디어 영역

        // 1) 저장될 경로
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        tv.setText(path.toString());

        // 2) 파일명
        // 이름이 동일하면 덮어쓰기가 되므로 이름이 겹치지 않도록
        // 보통 날짜를 이용하여 파일명 저장
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = "EX72_IMG_" + sdf.format(new Date()) + ".jpg";

        // 3) 경로 + 파일명.확장자
        File file = new File(path, fileName);
        tv.setText(file.toString());

        // 카메라 앱은 저장될 이미지의 실제 경로가 아니라 DB주소에 해당하는 콘텐츠 경로가 필요함.
        // 이 콘텐츠 경로를 Uri 라고 부름

        // 실제경로 (File 클래스 객체)를 콘텐츠 경로( Uri 객체 ) 로 변환
        // 다른앱에게 파일의 접근을 허용하려면 Provider를 이용해야 함.
        // 그 중에서 파일에 대한 경로 제공 Provider는 이미 클래스로 설계되어 있음.
        // FileProvider
        imgUri = FileProvider.getUriForFile(this, "sam", file);
        tv.setText(imgUri.toString());
    }
}