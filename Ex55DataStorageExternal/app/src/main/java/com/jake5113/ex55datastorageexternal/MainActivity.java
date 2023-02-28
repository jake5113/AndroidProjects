package com.jake5113.ex55datastorageexternal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    EditText et;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et);
        tv = findViewById(R.id.tv);

        findViewById(R.id.btn_save).setOnClickListener(view -> clickSave());
        findViewById(R.id.btn_load).setOnClickListener(view -> clickLoad());
    }

    private void clickSave() {

        // 외부 메모리(SD card)가 있는가?
        String state = Environment.getExternalStorageState();

        // 외부메모리의 상태가 연결(mounted)되어 있지 않은가를 확인
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            Snackbar.make(tv, "SD card is not mounted", Snackbar.LENGTH_LONG).show();
            return;
        }

        // 저장할 데이터
        String data = et.getText().toString();
        et.setText("");

        // "Data.txt"라는 파일에 데이터를 저장
        // 내부저장소와 다르게 액티비티에 스트림을 곧바로 열어주는 기능이 없음.
        // 그래서 직접 스트림을 열기위해 File 객체를 생성

        // 파일이 저장될 경로 : 앱에게 할당된 개별 영역
        File[] dirs = getExternalFilesDirs("MyDir"); // 하위 폴더명
        String s = "";
        for (File f : dirs) {
            s += f.getPath() + "\n";
        }
        tv.setText(s); // 경로 확인해보기

        // 여러 경로 중 첫번째가 보통 SDcard
        // 파일명과 경로를 결합하여 File 객체를 생성
        File file = new File(dirs[0], "Data.txt");

        // file 과 연결하는 무지개로드 만들기
        // 곧바로 문자스트림으로 만들기
        try {
            FileWriter fw = new FileWriter(file, true);
            PrintWriter writer = new PrintWriter(fw);
            writer.println(data);
            writer.flush();
            writer.close();

            tv.setText("SAVED");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void clickLoad() {

        // SD card 가 읽을 수 있는 상태인지 확인
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED) || state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {

            // 읽을 수 있는 상태...
            File[] dirs = getExternalFilesDirs("MyDir");
            File file = new File(dirs[0], "Data.txt");

            try {
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);
                StringBuffer buffer = new StringBuffer();
                while (true) {
                    String line = reader.readLine();
                    if (line == null) break;
                    buffer.append(line + "\n");
                }
                tv.setText(buffer.toString());

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}