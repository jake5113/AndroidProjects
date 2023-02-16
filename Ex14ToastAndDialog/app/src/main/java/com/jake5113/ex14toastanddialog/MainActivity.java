package com.jake5113.ex14toastanddialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn01, btn02;

    // 다이얼로그가 보여줄 목록의 항목들
    String[] items = new String[]{"Apple", "Banana", "Orange"};
    boolean[] checkedItems = new boolean[]{true, false, true};
    int selectedItemPosition = 0;

    TextView dialogTv;
    Button dialogBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn01 = findViewById(R.id.btn01);
        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast 객체 생성
                Toast.makeText(MainActivity.this, "Hello Toast", Toast.LENGTH_SHORT).show();
            }
        });

        btn02 = findViewById(R.id.btn02);
        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AlertDialog를 만들어주는 건축가(Builder) 객체 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                //건축가에게 원하는 모양을 의뢰(설정)
                builder.setTitle("다이얼로그");
                builder.setIcon(R.drawable.baseline_crisis_alert_24);

                // 1. 단순 메세지 1개 보여줄 때
                //builder.setMessage("Do u wanna Quit?");

                // 2. 목록으로 항목들 보여줄 때
//                builder.setItems(items, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // 두번째 파라미터 which : 클릭된 항목의 위치 인덱스번호 : 0,1,2
//                        Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
//                    }
//                });

                // 3. Single Choice 항목들 보여줄 때
//                builder.setSingleChoiceItems(items, selectedItemPosition, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        selectedItemPosition = which;
//                    }
//                });

                // 4. Multiple Choice 항목들 보여줄 때
//                builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                        // 두번째 파라미터 which : 클릭된 항목의 위치 인덱스 번호
//                        // 세번째 파라미터 isChecked : 클릭된 항목의 true/false 여부
//                        checkedItems[which] = isChecked;
//                    }
//                });

                // 5. Custrom View 보여주기
                // 직접 Java로 뷰들을 만들어서 설정하면 코드가 지저분
                // xml 언어로 뷰를 설계하면 편하게 적용가능함.
                // res폴더 안에 layout 폴더안에 dialog.xml 파일에 다이얼로그의 커스텀뷰 모양을 설계
                builder.setView(R.layout.dialog);


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Toast.makeText(MainActivity.this, "Okay bye~", Toast.LENGTH_SHORT).show();

                        // Single Choice 를 통해 선택한 아이템 문자열 출력해보기
                        // Toast.makeText(MainActivity.this, items[selectedItemPosition], Toast.LENGTH_SHORT).show();

                        // Multiple Choic 를 통해 선택된 아이템들의 문자열을 출력해보기
//                        String s = "";
//                        for (int i = 0; i < checkedItems.length; i++) {
//                            if (checkedItems[i]) s += items[i];
//                        }
//                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();

                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "CANCEL", Toast.LENGTH_SHORT).show();
                    }
                });
                // 건축가에게 의뢰(설정)한대로 다이얼로그를 만들어 달라고 요청
                AlertDialog dialog = builder.create();
                // 다이얼로그의 바깥쪽을 클릭했을때 없어지지(취소) 않도록
                dialog.setCanceledOnTouchOutside(false);
                // 다이얼로그 버튼이 아니면 어떤 방법으로도 취소 못하게
                dialog.setCancelable(false);
                //다이얼로그를 보이기
                dialog.show();

                // 다이얼로그 안에 있는 Custom View의 뷰들을 찾아서 제어하기
                dialogTv = dialog.findViewById(R.id.dialog_tv);
                dialogBtn = dialog.findViewById(R.id.dialog_btn);
                dialogBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogTv.setText("Good");
                    }
                });
            }
        });
    }
}