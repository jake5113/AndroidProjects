package com.jake5113.ex27recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Item> items;

    public MyAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override // 재활용할 뷰가 없으면 뷰를 만들기 위해 자동으로 호출되는 메소드
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recyclerview_item, parent, false);
        VH holder = new VH(itemView);
        return holder;
    }

    @Override // 현재 연결할 번째(position) 데이터를 뷰에 넣어주는 작업을 위해 호출되는 메소드
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // 첫번째 파라미터 holder가 가진 뷰들 참조변수를 통해 값 설정
        VH vh = (VH) holder;

        // 현재번째 아이템요소를 얻어와서 뷰들에 설정
        Item item = items.get(position);
        vh.tvName.setText(item.name);
        vh.tvMsg.setText(item.message);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // 아이템 1개 뷰 안에 있는 자식뷰들의 참조값을 저장하는 참조변수들을 멤버로 가지는 이너 클래스
    class VH extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvMsg;

        public VH(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvMsg = itemView.findViewById(R.id.tv_msg);

            // 항목뷰를 클릭했을때 반응하는 리스너 처리
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // 클릭한 아이템의 위치 인덱스 번호
                    int position = getLayoutPosition();
                    // 클릭한 번째 아이템요소 데이터 얻어오기
                    Item item = items.get(position);

                    Toast.makeText(context, item.name , Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
