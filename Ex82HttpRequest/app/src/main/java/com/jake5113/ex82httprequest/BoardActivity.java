package com.jake5113.ex82httprequest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.jake5113.ex82httprequest.databinding.ActivityBoardBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {

    ActivityBoardBinding binding;

    ArrayList<Item> items = new ArrayList<>();
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter = new MyAdapter(this, items);
        binding.recycler.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(); // 서버에서 데이터를 읽어오도록 요청
    }

    private void loadData() {
        // 테스트 데이터를 추가해보기
//        items.add(new Item(1, "sam", "hello", "1234-56-78"));
//        items.add(new Item(2, "sam", "hello", "1234-56-78"));
//        adapter.notifyDataSetChanged();

        //서버 DB에 저장된 데이터들을 읽어오기
        // 네트워크 작업 스레드
        new Thread() {
            @Override
            public void run() {
                // 서버 DB값을 echo 해주는 php 문서를 실행
                String serverAddress = "http://jake5113.dothome.co.kr/Android/loadDB.php";

                try {
                    URL url = new URL(serverAddress);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    connection.setUseCaches(false);

                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    StringBuffer buffer = new StringBuffer();
                    while (true) {
                        String line = reader.readLine();
                        if (line == null) break;

                        buffer.append(line + "\n");
                    }

                    // 잘 읽어 왔는지 확인용
//                    runOnUiThread(() -> {
//                        new AlertDialog.Builder(BoardActivity.this).setMessage(buffer.toString()).create().show();
//                    });

                    // 서버에서 echo 된 문자열 데이터에서 '&'문자를 기준으로 문자열을 분리
                    // 한 줄 단위(Item)로 데이터를 분리
                    String[] rows = buffer.toString().split("&");
                    Log.i("TAG", rows.length + "");

                    // 한 줄 데이터의 콤마 구분자를 분리하여 값들 분석하기[csv 파싱]
                    for (String row : rows) {
                        String[] datas = row.split(",");
                        if (datas.length != 4) continue;

                        int no = Integer.parseInt(datas[0]);
                        String name = datas[1];
                        String msg = datas[2];
                        String date = datas[3];

                        items.add(new Item(no, name, msg, date));
                    }

                    runOnUiThread(() -> {
                        adapter.notifyDataSetChanged();
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