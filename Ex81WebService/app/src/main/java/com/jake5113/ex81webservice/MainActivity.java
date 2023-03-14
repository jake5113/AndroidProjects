package com.jake5113.ex81webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.jake5113.ex81webservice.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    // activity_main.xml 문서와 연결되어 뷰들을 제어할 수 있도록 설계된
    // Binding 클래스 참조변수
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn.setOnClickListener(v -> clickBtn());
        binding.btn2.setOnClickListener(v -> clickBtn2());
    }
    
    private void clickBtn2() {
        // 웹 서버의 이미지를 읽어오기 - 스레드와 스트림 이용 해야 함.
        // 이미지 로드 라이브러리를 활용 : Glide or Picasso
        String address = "http://jake5113.dothome.co.kr/newyork.jpg";
        Glide.with(this).load(address).into(binding.iv);
    }

    private void clickBtn() {
        // 웹서버에 접속하여 index.html 문서를 읽어와서 TextView에 보여주기
        // 네트워크 작업은 별도의 Thread가 해야만 함.
        Thread t = new Thread() {
            @Override
            public void run() {
                String address = "http://jake5113.dothome.co.kr/";

                // Stream(무지개로드) 를 열어주는 URL(해임달)
                try {
                    URL url = new URL(address);
                    InputStream is = url.openStream(); // 바이트 스트림
                    InputStreamReader isr = new InputStreamReader(is); // 문자 스트림 변환
                    BufferedReader reader = new BufferedReader(isr); // 보조문자 스트림
                    StringBuffer buffer = new StringBuffer();
                    while (true) {
                        String line = reader.readLine();
                        if (line == null) break;
                        buffer.append(line).append("\n");
                    }
                    // 별도 Thread는 UI 변경 불가능.
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.tv.setText(buffer.toString());
                        }
                    });

                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        t.start();
    }
}