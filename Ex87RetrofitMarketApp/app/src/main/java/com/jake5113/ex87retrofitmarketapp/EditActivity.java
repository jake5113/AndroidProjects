package com.jake5113.ex87retrofitmarketapp;

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
import com.jake5113.ex87retrofitmarketapp.databinding.ActivityEditBinding;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditActivity extends AppCompatActivity {

    ActivityEditBinding binding;

    // 업로드 할 이미지의 파일경로 변수
    String imgPath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("글 작성"); // 제목 글씨
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼

        binding.btnSelect.setOnClickListener(view -> clickSelect());
        binding.btnComplete.setOnClickListener(view -> clickComplete());
    }

    private void clickSelect() {
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_CANCELED) return;
        Uri uri = result.getData().getData();
        Glide.with(this).load(uri).into(binding.iv);
        // Retrofit 을 이용하여 서버에 이미지를 보낼때는 Uri 주소가 아니라 파일의 주소가 필요함.
        imgPath = getFilePathFromUri(uri);
        new AlertDialog.Builder(this).setMessage(imgPath).show();
    });

    //Uri -- > 절대경로로 바꿔서 리턴시켜주는 메소드
    String getFilePathFromUri(Uri uri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    private void clickComplete() {
        // 사용자가 입력한 데이터를 서버에 전송하기

        // 전송할 데이터 [ name, title, message, price, imgPath(파일) ]
        String name = binding.etName.getText().toString();
        String title = binding.etTitle.getText().toString();
        String message = binding.etMsg.getText().toString();
        String price = binding.etPrice.getText().toString();

        // Retrofit 작업 5단계
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        // String 데이터들
        Map<String, String> dataPart = new HashMap<>();
        dataPart.put("name", name);
        dataPart.put("title", title);
        dataPart.put("msg", message);
        dataPart.put("price", price);

        // 이미지 파일 택배박스 포장
        MultipartBody.Part filePart = null;
        if (imgPath != null) {
            File file = new File(imgPath);
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file); // 비닐팩 포장
            filePart = MultipartBody.Part.createFormData("img", file.getName(), body);
        }

        Call<String> call = retrofitService.postDataToServer(dataPart, filePart);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s = response.body();
                Toast.makeText(EditActivity.this, s, Toast.LENGTH_SHORT).show();
                
                // 게시글 업로드가 성공했으니 글작성 화면은 자동 종료
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(EditActivity.this, "error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() { // up 버튼 클릭 리스너
        finish();
        return super.onSupportNavigateUp();
    }
}