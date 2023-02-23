package com.jake5113.ex44activity3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(view -> {
            // 결과를 받기 위해 SecondActivity를 실행
            Intent intent = new Intent(this, SecondActivity.class);
            resultLauncher.launch(intent);

        });
    }

    // 결과를 받기 위한 액티비티를 대신 실행시켜 주기 위한 하청업체 객체 생성 및 등록
    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {

            // 혹시 실행시켰던 액티비티에서 [뒤로가기 버튼]으로 취소했을 수도 있어서...
            if (result.getResultCode() == RESULT_OK) {
                // 돌아온 Intent 객체를 소환
                Intent intent = result.getData();
                String name = intent.getStringExtra("name");
                int age = intent.getIntExtra("age", 0);

                tv.setText(name + " : " + age);


            } else if (result.getResultCode() == RESULT_CANCELED) {
                Toast.makeText(MainActivity.this, "취소 했습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    });

}