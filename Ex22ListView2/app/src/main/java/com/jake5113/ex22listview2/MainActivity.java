package com.jake5113.ex22listview2;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // 대량의 데이터를 저장할 리스트 객체
    ArrayList<String> datas = new ArrayList<>();

    // 대량의 데이터(String)를 적적한 뷰(TextView) 객체로 만들어주는 Adapter 객체의 참조변수
    ArrayAdapter adapter;
    ListView listView;
    EditText et;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 대량의 데이터들 추가
        datas.add(new String("aaa"));
        datas.add(new String("bbb"));
        datas.add("ccc"); // 자동 new String()

        // 어댑터 객체 생성
        adapter = new ArrayAdapter(this, R.layout.listview_item, datas);
        listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);

        //리스트의 항목을 클랙했을때 반응하기
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, datas.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        // 새로운 데이터를 추가하기
        et = findViewById(R.id.et);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText에 써있는 글씨를 얻어와서
                String data = et.getText().toString();
                et.setText("");

                // 대량의 데이터인 datas(리스트객체)에 추가하기
                datas.add(data);
                // adapter에게 데이터의 개수가 변경되었다는 것을 공지해 줘야 화면에 갱신됨
                adapter.notifyDataSetChanged();
                //listView.setAdapter(adapter);

                // 리스트뷰의 스크롤 위치를 지정
                listView.setSelection(datas.size()-1);

            }
        });

        //리스트의 아이템을 롱~~~ 클릭했을때 반응하기 -- 그 데이터 삭제하기
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                // 대량의 데이터 datas 리스트 객체에서 현재 롱클릭한 아이템의 위치 요소를 제거
                datas.remove(position);
                adapter.notifyDataSetChanged();

                // return 을 true로 해야 이벤트가 여기서 끝나게 되어 onClick()메소드가 이어서 발동되지 않음.(consume)
                return true;
            }
        });

    }
}