package com.jake5113.ex29viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

    Context context;
    ArrayList<Integer> imgIds;

    public MyAdapter(Context context, ArrayList<Integer> imgIds) {
        this.context = context;
        this.imgIds = imgIds;
    }

    @NonNull
    @Override

    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.page, parent, false);
        VH holder = new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.iv.setImageResource(imgIds.get(position));
    }

    @Override
    public int getItemCount() {
        return imgIds.size();
    }

    class VH extends RecyclerView.ViewHolder {
        ImageView iv;

        public VH(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);

        }
    }
}
