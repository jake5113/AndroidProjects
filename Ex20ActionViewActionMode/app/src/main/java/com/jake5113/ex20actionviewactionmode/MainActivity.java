package com.jake5113.ex20actionviewactionmode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText actionViewEt;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 액션모드 메뉴 : 새로운 액션바를 만들어서 옵션메뉴를 붙이는 방식

                // 액션모드 시작
                startActionMode(new ActionMode.Callback() {
                    @Override // 액션모드가 처음 실행될 때 메뉴항목들을 만들기 위해 자동실행되는 메소드
                    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                        mode.getMenuInflater().inflate(R.menu.actionmode, menu);

                        // 액션모드에 의해 만들어진 제목줄(ActionBar)의 제목 글씨 표시
                        mode.setTitle("ACTION MODE");
                        mode.setSubtitle("This is action mode");

                        // 액션모드의 배경색은 테마(themes.xml)에서 지정

                        return true; // 리턴값이 true 여야만 액션모드가 발동함
                    }

                    @Override // 액션모드가 시작될때 마다 실행되는 콜백메소드
                    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                        return false;
                    }

                    @Override // 메뉴항목이 클릭됐을때 자동 실행되는 콜백메소드
                    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                        if (item.getItemId() == R.id.menu_aa)
                            Toast.makeText(MainActivity.this, "SHARE", Toast.LENGTH_SHORT).show();
                        else if (item.getItemId() == R.id.menu_bb)
                            Toast.makeText(MainActivity.this, "MAP", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode mode) {

                    }
                });

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);

        // ActionView로 지정한 커스텀뷰안에 있는 EditText를 찾아오기
        MenuItem item = menu.findItem(R.id.menu_action);
        LinearLayout layout = (LinearLayout) item.getActionView();
        actionViewEt = layout.findViewById(R.id.actionview_et);

        // EditText의 소프트키보드 중에서 작성완료버튼(Search모양 버튼)을 클릭하는 것에
        // 반응하는 리스너 객체 생성 및 설정
        actionViewEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                // 키보드에서 어떤 키를 눌렀는지를 가지고 있는 변수 : 두번째 파라미터 actionId
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String message = actionViewEt.getText().toString();
                    Toast.makeText(MainActivity.this, "검색어 : " + message, Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}