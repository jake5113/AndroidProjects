package com.jake5113.ex28recyclerview2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

    Context context;
    ArrayList<Item> items;

    public MyAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        VH holder = new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        // 현재번째 아이템요소 얻어오기
         Item item = items.get(position);

         //VH 객체가 가지고 있는 자식뷰들에 아이템의 값을 설정(연결)
        holder.tvName.setText(item.name);
        holder.tvRole.setText(item.role);

        holder.civProfile.setImageResource(item.profileImgId);
        holder.ivImg.setImageResource(item.imgId);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // 아이템뷰 1개안에 있는 자식뷰들의 참조변수를 저장하고 있는 이너클래스
    class VH extends RecyclerView.ViewHolder{

        CircleImageView civProfile;
        TextView tvName;
        TextView tvRole;
        ImageView ivImg;

        public VH(@NonNull View itemView) {
            super(itemView);

            civProfile = itemView.findViewById(R.id.civ_profile);
            tvName = itemView.findViewById(R.id.tv_name);
            tvRole = itemView.findViewById(R.id.tv_role);
            ivImg = itemView.findViewById(R.id.iv_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 클릭한 아이템의 위치 인덱스번호 얻어오기
                    int position = getLayoutPosition();
                    Item item = items.get(position);
                    Toast.makeText(context, item.name + " " + item.role , Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
} // MyAdapter class..
