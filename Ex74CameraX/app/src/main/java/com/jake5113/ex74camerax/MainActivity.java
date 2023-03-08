package com.jake5113.ex74camerax;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.common.util.concurrent.ListenableFuture;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    // CameraX library 추가
    PreviewView previewView;
    CircleImageView civ;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previewView = findViewById(R.id.preview_view);
        findViewById(R.id.fab).setOnClickListener(v -> clickBtn());
        tv = findViewById(R.id.tv);
        civ = findViewById(R.id.civ);
        // 상태표시줄 영역까지 액티비티를 확장
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        // 동적퍼미션 처리
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.CAMERA);
        permissions.add(Manifest.permission.RECORD_AUDIO);
        if (Build.VERSION.SDK_INT <= 28)
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);


        int checkResult = checkSelfPermission(permissions.get(0)); // CAMERA
        if (checkResult == PackageManager.PERMISSION_DENIED) {
            // 퍼미션들을 요청하는 대행사 이용
            // 리스트 --> 배열
            String[] arr = new String[permissions.size()];
            permissions.toArray(arr);
            resultLauncher.launch(arr);
        }
    }

    // 이미지 캡처 객체 참조변수
    ImageCapture imageCapture = null;

    // 요청 퍼미션들 배열
    String[] permissionArray;

    // 퍼미션들을 요청하고 결과를 받아주는 계약을 체결하는 대행사 등록
    ActivityResultLauncher<String[]> resultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
        @Override
        public void onActivityResult(Map<String, Boolean> result) {
            // Map 컬렉션은 foreach 문으로 처리 불가능
            // 그래서 우선 키값들만 빼오기
            Set<String> keys = result.keySet();
            for (String key : keys) {
                boolean value = result.get(key);
                if (value)
                    Toast.makeText(MainActivity.this, key + "를 허용하셨습니다.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "key + 를 허용하지 않았습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    });

    // 프리뷰 기능을 시작하는 작업 메소드
    void startCamera() {

        ListenableFuture<ProcessCameraProvider> listenableFuture = ProcessCameraProvider.getInstance(this);

        listenableFuture.addListener(new Runnable() {
            @Override
            public void run() {
                // 카메라 기능 제공자
                try {
                    ProcessCameraProvider cameraProvider = listenableFuture.get();

                    // Preview 작업을 하는 객체
                    Preview.Builder builder = new Preview.Builder();
                    Preview preview = builder.build();
                    preview.setSurfaceProvider(previewView.getSurfaceProvider());

                    CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;

                    // 이미지 캡처 객체 생성
                    imageCapture = new ImageCapture.Builder().build();

                    cameraProvider.bindToLifecycle(MainActivity.this, cameraSelector, preview, imageCapture);

                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, ContextCompat.getMainExecutor(this));

    }

    // 이미지 캡쳐 버튼 클릭
    private void clickBtn() {
        if (imageCapture == null) return;

        // 저장될 파일명 - 날짜와 시간 이용
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = sdf.format(System.currentTimeMillis());

        // CameraX의 미디어 DB에 저장할 한 줄(record: 하나의 파일정보) 객체
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
        values.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/CameraX-Image");
        // 이미지 캡처에게 저장 옵션으로 설정하기 위해 옵션객체 생성
        ImageCapture.OutputFileOptions outputOptions = new ImageCapture.OutputFileOptions.Builder(getContentResolver(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values).build();

        // 이미지 캡처에게 촬영을 요청 - 위 옵션에 설정한 위치에 저장됨
        imageCapture.takePicture(outputOptions, ContextCompat.getMainExecutor(this), new ImageCapture.OnImageSavedCallback() {
            @Override
            public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                Toast.makeText(MainActivity.this, "촬영성공", Toast.LENGTH_SHORT).show();

                tv.setText(outputFileResults.toString());
                Glide.with(MainActivity.this).load(outputFileResults.getSavedUri()).into(civ);
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                Toast.makeText(MainActivity.this, "ERROR : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    // 액티비티가 화면에 완전히 보여질때 자동으로 발동하는 콜백메소드
    @Override
    protected void onResume() {
        super.onResume();
        startCamera();
    }
}