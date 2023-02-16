package com.jake5113.ex17contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "clicked button", Toast.LENGTH_SHORT).show();

            }
        });

        // 액티비티에게 btn객체를 ContextMenu로 등록
        registerForContextMenu(btn);
    }

    // Context Menu로 등록된 View를 롱 클릭하면 컨텍스트메뉴를 만드는 메소드가 자동 발동함
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // menu 폴더안에 context.xml 파일을 읽어서 메뉴아이템객체로 생성(부풀리다)
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    // ContextMenu의 항목(MenuItem)을 선택했을때 자동으로 발동하는 콜백 메소드

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menu_save) Toast.makeText(this, "SAVE", Toast.LENGTH_SHORT).show();
        else if (item.getItemId() == R.id.menu_delete) { Toast.makeText(this, "DELETE", Toast.LENGTH_SHORT).show();

        }
        return super.onContextItemSelected(item);
    }
}