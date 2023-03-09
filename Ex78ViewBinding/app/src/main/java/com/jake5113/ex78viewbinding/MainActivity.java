package com.jake5113.ex78viewbinding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.jake5113.ex78viewbinding.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // ViewBinding 은 라이브러리가 아니고 안드로이드 아키텍쳐 구성요소임.
    // 그래서 그냥 기능만 ON 하면 됨.

    //activity_main.xml 과 연결되어 뷰들의 참조변수를 멤버로 가지고 있는 Binding 클래스
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Binding 객체가 만든 뷰를 액티비티가 보여줘야 하기에 주석처리!
        //setContentView(R.layout.activity_main);

        // Binding 객체생성 - activity_main.xml을 객체로 생성하여 액티비티에 뷰로 설정
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 1) TextView 글씨 변경 - 이미 binding 객체가 TextView를 참조하고 있음
        binding.tv.setText("Hello Android");

        // 2) Button 클릭 이벤트
        binding.btn.setOnClickListener(v -> {
            binding.tv.setText("Have a good day");
        });

        // 2.1) Button 롱클릭이벤트
        binding.btn.setOnLongClickListener(v -> {
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
            return true;
        });

        // 3) 사용자 입력받아 TextView에 보이기
        binding.btn2.setOnClickListener(view -> {
            binding.tvResult.setText(binding.et.getText().toString());
            binding.et.setText("");
        });

        // 4) 화면의 일부분을 별도로 설계하여 관리하는 Fragment 에서 ViewBinding

        // 5) 앱개발에 가장 많이 사용되는 View 중 RecyclerView 에서 viewBinding 사용해보기


    }

    // 대량의 데이터들
    ArrayList<ItemVO> items = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();

        // 임의의 데이터들 추가
        for (int i = 0; i < 3; i++) {
        items.add(new ItemVO("newyork", R.drawable.newyork));
        items.add(new ItemVO("paris", R.drawable.paris));
        items.add(new ItemVO("sydney", R.drawable.sydney));
        }

        // 리사이클러에 아답터 설정
        binding.recycler.setAdapter(new MyAdapter(this, items));

    }
}