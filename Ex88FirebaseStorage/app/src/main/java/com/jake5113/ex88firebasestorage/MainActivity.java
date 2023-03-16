package com.jake5113.ex88firebasestorage;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jake5113.ex88firebasestorage.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    // Firebase : 안드로이드앱을 개발할 때 서버단 작업을 코딩한줄 없이 사용하는 플랫폼

    // 이 프로젝트와 Firebase 플랫폼을 연동하기! [firebase.com 가이드 문서 참고]
    // 파이어베이스 사이트에 로그인하고 console에서 [프로젝트 만들기] 를 통해 작업시작

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLoad.setOnClickListener(v -> clickLoad());
        binding.btnSelect.setOnClickListener(v -> clickSelect());
        binding.btnUpload.setOnClickListener(v -> clickUpload());
    }

    private void clickSelect() {
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
    }

    // 선택된 이미지의 Uri
    Uri imgUri = null;

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_CANCELED) return;
        imgUri = result.getData().getData();
        Glide.with(this).load(imgUri).into(binding.iv);
    });


    private void clickUpload() {
        if (imgUri == null) return;

        //Firebase Storage 에 파일 업로드 하기
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

        // 저장할 파일명이 중복되지 않도록 .. 날짜로 변수명 정하기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = "IMG_" + sdf.format(new Date()) + ".png";

        // 저장할 파일 위치에 대한 참조객체
        StorageReference imgRef = firebaseStorage.getReference("photo/" + fileName); // photo 폴더가 없으면 만들고 .. 있으면 그냥 참조

        // 위 저장경로 참조객체에게 실제 파일 업로드 시키기
        imgRef.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(MainActivity.this, "업로드 성공", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "업로드 실패 : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clickLoad() {
        // Firebase Storage 에 저장되어 있는 이미지 파일 읽어오기

        // Firebase Storage 관리객체 소환
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

        // 저장소의 최상위 참조객체 얻어오기
        StorageReference rootRef = firebaseStorage.getReference();
        // 읽어오기를 원하는 파일의 참조객체 얻어오기
        StorageReference imgRef = rootRef.child("ball.png");
        if (imgRef != null) {
            // 파일 참조객체로 부터 이미지의 [다운로드 URL] 얻어오기
            imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(MainActivity.this).load(uri).into(binding.iv);
                }
            });
        }

    }
}