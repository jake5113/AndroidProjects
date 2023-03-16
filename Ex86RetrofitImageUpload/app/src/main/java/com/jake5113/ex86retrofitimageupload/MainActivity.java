package com.jake5113.ex86retrofitimageupload;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jake5113.ex86retrofitimageupload.databinding.ActivityMainBinding;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    // 업로드 할 파일의 주소를 저장하는 문자열 변수
    String imgPath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSelect.setOnClickListener(v -> clickSelect());
        binding.btnUpload.setOnClickListener(v -> clickUpload());
    }

    private void clickSelect() {
        // 이미지를 선택할 수 있는 앱(Activity)을 실행
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);

    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_CANCELED) return;

        // 선택한 사진의 콘텐츠 주소 URI 정보 얻기
        Uri uri = result.getData().getData();
        Glide.with(this).load(uri).into(binding.iv);
        // 애석하게 Retrofit을 이용하여 서버에 파일을 전송하려면
        // 파일의 Uri(즉, 콘텐츠 DB 주소)가 아니라 파일(File)의 주소가 필요함
        // new AlertDialog.Builder(this).setMessage(uri.toString()).create().show();

        // uri --> 파일주소 변환
        imgPath = getFilePathFromUri(uri);
        new AlertDialog.Builder(this).setMessage(imgPath).create().show();

    });

    // uri --> 파일경로로 바꿔서 리턴해주는 메소드
    private String getFilePathFromUri(Uri uri) {
        String[] proj = new String[]{MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }


    private void clickUpload() {
        // Retrofit 라이브러리를 이용하여 이미지 업로드

        // 1) Retrofit 객체 생성
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://jake5113.dothome.co.kr");
        builder.addConverterFactory(ScalarsConverterFactory.create());
        Retrofit retrofit = builder.build();

        // 2) Service 인터페이스 와 추상메소드 설계 [요구 명세]

        // 3) Service 인터페이스 객체 생성
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        // 4) 보낼 파일을 택배상자[MultipartBody.Part]로 포장
        File file = new File(imgPath);
        RequestBody body = RequestBody.create(MediaType.parse("image/*"), file); //진공팩 포장
        MultipartBody.Part part = MultipartBody.Part.createFormData("img", file.getName(), body); // 택배상자 포장

        Call<String> call = retrofitService.uploadImage(part);

        // 5) 작업시작
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s = response.body();
                new AlertDialog.Builder(MainActivity.this).setMessage(s).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}