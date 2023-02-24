package com.jake5113.ex49threadnetworkimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btn, btn2;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        iv = findViewById(R.id.iv);

        btn.setOnClickListener(view -> {
            clickBtn();
        });

        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(view -> {
            // 이미지 로드 라이브러리(Glide, Picasso) 사용해보기
            String imgUrl = "https://cdn.pixabay.com/photo/2023/01/02/04/13/dog-7691238_960_720.jpg";
            Glide  .with(this).load(imgUrl).into(iv);
        });
    }

    void clickBtn() {
        // 네트워크상에 있는 이미지를 읽어와서 이미지뷰에 보여주기
        // 네트워크 작업은 오래걸리는 작업으로 인식되기에 반드시 별도의 Thread가 해야만 함.
        // ** 주의 ! ** 네트워크작업은 반드시 허가(퍼미션)을 받아야함 [ AndroidManifest.xml 에서 작업]

        new Thread() {
            @Override
            public void run() {
                // 이미지의 인터넷 주소
                String imgUrl = "https://cdn.pixabay.com/photo/2023/01/31/05/59/zebra-7757193_960_720.jpg";


                // 서버 주소까지 연결되는 무지개로드(Stream) 열기
                try {
                    // Stream 을 열 수 있는 해임달객체(URL) 생성
                    URL url = new URL(imgUrl);

                    // 무지개로드 열기
                    InputStream is = url.openStream();

                    // 스트림을 통해 이미지를 읽어와서 Bitmap 객체로 생성하기
                    Bitmap bm = BitmapFactory.decodeStream(is);

                    // 이미지뷰에 Bitmap을 설정
                    // iv.setImageBitmap(bm); // ERROR - UI 작업은 main Thread 만 할 수 있음.

                    // UI thread(Main Thread) 에서 ui 작업을 하도록 요청
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            iv.setImageBitmap(bm);
                        }
                    });


                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }
}