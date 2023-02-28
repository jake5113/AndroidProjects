package com.jake5113.ex57datastoragesqlitedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText etName, etAge, etEmail;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        etEmail = findViewById(R.id.et_email);

        findViewById(R.id.btn_insert).setOnClickListener(v -> clickInsert());
        findViewById(R.id.btn_update).setOnClickListener(v -> clickUpdate());
        findViewById(R.id.btn_delete).setOnClickListener(v -> clickDelete());
        findViewById(R.id.btn_select_all).setOnClickListener(v -> clickSelectAll());
        findViewById(R.id.btn_select_by).setOnClickListener(v -> clickSelectByName());

        // test.db 라는 이름으로 데이터베이스 파일 열기 또는 생성
        // 액티비티클래스에 이미 이 기능이 존재함.
        // 이 메소드를 실행하면 test.db를 제어할 수 있는 능력을 가진 객체(SQLiteDatabase)가 리턴됨
        db = openOrCreateDatabase("test", MODE_PRIVATE, null);

        // 만들어진 DB 파일에 테이블(표)를 생성하는 작업 수행
        // SQL 언어를 이용해서 원하는 명령어를 Database에 수행함.
        db.execSQL("CREATE TABLE IF NOT EXISTS member(num INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20) NOT NULL, age INTEGER, email TEXT)");

    }

    private void clickInsert() {
        String name = etName.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());
        String email = etEmail.getText().toString();

        // member 라는 이름의 테이블(표)에 값을 삽입하는 쿼리문(SQL)
        db.execSQL("INSERT INTO member (name, age, email) VALUES ('" + name + "','" + age + "','" + email + "')");
    }

    private void clickUpdate() {

        // 업데이트할 데이터의 이름값
        String name = etName.getText().toString();
        db.execSQL("UPDATE member SET age=30 WHERE name = ?", new String[]{name});
    }

    private void clickDelete() {
        String name = etName.getText().toString();
        db.execSQL("DELETE FROM member WHERE name = ?", new String[]{name});
    }

    private void clickSelectAll() {
        // member 테이블의 모든 데이터들을 검색하여 가져오기
        Cursor cursor = db.rawQuery("SELECT * FROM member", null);
        if (cursor == null) return; // 조건이 잘못되었을때

        int cnt = cursor.getCount(); // 총 줄(row : 레코드) 수
        cursor.moveToFirst(); // 첫 레코드(줄)로 이동

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < cnt; i++) {
            int num = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String email = cursor.getString(3);

            buffer.append(num + " " + name + " " + age + " " + email + "\n");
            cursor.moveToNext(); // 다음 레코드로 이동
        }

        // 화면에 결과 보여주기
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(buffer.toString());
        builder.create().show();
    }

    private void clickSelectByName() {
        String name = etName.getText().toString();
        Cursor cursor = db.rawQuery("SELECT name, age FROM member WHERE name = ?", new String[]{name});
        if (cursor == null) return;

        int cnt = cursor.getCount();
        cursor.moveToFirst();

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < cnt; i++) {
            String name2 = cursor.getString(0);
            int age = cursor.getInt(1);

            buffer.append(name2 + " : " + age + "\n");
            cursor.moveToNext();
        }
        new AlertDialog.Builder(this).setMessage(buffer.toString()).create().show();
    }
}