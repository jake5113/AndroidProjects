package com.jake5113.ex19searchview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SearchView searchView; // 참조변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    // onCreate() 메소드가 실행된 후 Option Menu를 만드는 작업을 하는 콜백메소드가 자동 발동


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);

        // SearchView 찾아오기
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        searchView = (SearchView) menuItem.getActionView();

        // SearchView에 적용하는 설정
        searchView.setQueryHint("이름을 입력하세요");

        // SearchView에 글씨변화에 반응하는 리스너 설정
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override // 소프트키보드의 [돋보기 버튼]을 선택했을때 (검색어 입력을 완료)
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "검색어 : " + query, Toast.LENGTH_SHORT).show();
                return false;
            }


            @Override // 글씨가 변경될때마다 실행되는 콜백메소드
            public boolean onQueryTextChange(String newText) {


                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}