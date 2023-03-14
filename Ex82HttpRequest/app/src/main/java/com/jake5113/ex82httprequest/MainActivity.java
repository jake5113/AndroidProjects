package com.jake5113.ex82httprequest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.jake5113.ex82httprequest.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.SNIHostName;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGet.setOnClickListener(v -> clickGet());
        binding.btnPost.setOnClickListener(v -> clickPost());
        binding.btnLoad.setOnClickListener(v -> {
            Intent intent = new Intent(this, BoardActivity.class);
            startActivity(intent);
        });
    }

    private void clickGet() {
        // 네트워크 작업 Thread
        new Thread() {
            @Override
            public void run() {

                // 서버로 보낼 데이터들
                String name = binding.etName.getText().toString();
                String message = binding.etMsg.getText().toString();

                // GET 방식으로 보낼 서버의 주소
                String serverAddress = "http://jake5113.dothome.co.kr/Android/getTest.php";

                // GET 방식은 보낼 데이터(name, message)를 URL 주소 뒤에 붙여서 보내는 방식
                // 단, URL 에는 한글 및 특수문자 사용 불가 - 한글을 URL 에 사용될 수 있도록 암호화(인코딩)
                try {
                    name = URLEncoder.encode(name, "utf-8");
                    message = URLEncoder.encode(message, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }

                String getAddress = serverAddress + "?name=" + name + "&msg=" + message;

                // 서버와 연결작업
                try {
                    URL url = new URL(getAddress);
                    //url.openStream(); // URL 은 InputStream 만 열 수 있음
                    // OutputStream 까지 하는 Http 통신용 객체를 이용해야 함.
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET"); // 대문자
                    connection.setDoInput(true);
                    connection.setUseCaches(false);

                    // GET 방식은 이미 URL 에 데이터가 추가되어 있어서 별도로 OutputStream이 필요하지 않음.

                    // 서버로부터 응답(Response)된 결과를 받아보기 위해
                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    StringBuffer buffer = new StringBuffer();
                    while (true) {
                        String line = reader.readLine();
                        if (line == null) break;

                        buffer.append(line + "\n");
                    }

                    runOnUiThread(() -> {
                        binding.tv.setText(buffer.toString());
                    });

                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }.start();
    }

    private void clickPost() {
        // 네트워크 작업 스레드
        new Thread() {
            @Override
            public void run() {

                // 서버로 보낼 데이터들
                String name = binding.etName.getText().toString();
                String message = binding.etMsg.getText().toString();

                //POST 방식으로 데이터를 보낼 서버 주소
                String serverAddress = "http://jake5113.dothome.co.kr/Android/postTest.php";

                // 서버와 통신연결
                try {
                    URL url = new URL(serverAddress);
                    // Http 통신용 객체를 만들기
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);

                    // 보낼 데이터들 POST 방식으로 쓰기 위해 [key=value] 규칙에 맞게 하나의 문자열로 결합
                    String data = "name=" + name + "&msg=" + message;

                    // 데이터를 OutputStream을 이용하여 직접 내보내기
                    OutputStream os = connection.getOutputStream();
                    OutputStreamWriter writer = new OutputStreamWriter(os);

                    writer.write(data, 0, data.length());
                    writer.flush();
                    writer.close();

                    // 서버(postTest.php)에서 echo 한 응답 문자열 읽어오기
                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    StringBuffer buffer = new StringBuffer();
                    while (true) {
                        String line = reader.readLine();
                        if (line == null){ break;}

                        buffer.append(line + "\n");
                    }

                    runOnUiThread(() -> {
                        binding.tv.setText(buffer.toString());
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