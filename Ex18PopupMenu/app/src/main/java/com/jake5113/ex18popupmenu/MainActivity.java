package com.jake5113.ex18popupmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // PopupMenu 객체 생성
                //PopupMenu popupMenu = new PopupMenu(MainActivity.this, btn); // 두번째 파라미터 : 메뉴가 보여질 뷰
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, tv);
                // 팝업메뉴가 보여줄 메뉴설계 [ menu 폴더안에 popup.xml파일 ]
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.popup, popupMenu.getMenu());

                popupMenu.show();

                // 팝업메뉴항목이 선택되었을때 반응하기
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        if (item.getItemId() == R.id.menu_info)
                            Toast.makeText(MainActivity.this, "information", Toast.LENGTH_SHORT).show();
                        else if (item.getItemId() == R.id.menu_delete)
                            Toast.makeText(MainActivity.this, "delete", Toast.LENGTH_SHORT).show();
                        else if (item.getItemId() == R.id.menu_modify)
                            Toast.makeText(MainActivity.this, "modify", Toast.LENGTH_SHORT).show();


                        return false;
                    }
                });
            }
        });
    }
}