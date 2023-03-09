package com.jake5113.ex78viewbinding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jake5113.ex78viewbinding.databinding.RecyclerItemBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

    Context context;
    ArrayList<ItemVO> items;

    public MyAdapter(Context context, ArrayList<ItemVO> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.binding.tv.setText(items.get(position).title);
        holder.binding.iv.setImageResource(items.get(position).imgResId);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        // recycler_item.xml 과 연결되는 바인딩 클래스 침조변수
        RecyclerItemBinding binding;

        public VH(@NonNull View itemView) {
            super(itemView);
            // 이미 만든 뷰객체의 안에 있는 자식뷰들과 연결하는 바인딩 객체
            binding = RecyclerItemBinding.bind(itemView);
        }
    }
}
