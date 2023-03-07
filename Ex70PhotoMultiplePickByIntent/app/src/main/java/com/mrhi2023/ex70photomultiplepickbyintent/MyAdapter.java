package com.mrhi2023.ex70photomultiplepickbyintent;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH>{

    Context context;
    ArrayList<Uri> images;

    public MyAdapter(Context context, ArrayList<Uri> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.page, parent, false);
        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        //holder.pv.setImageURI(images.get(position));
        Glide.with(context).load(images.get(position)).into(holder.pv);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    class VH extends RecyclerView.ViewHolder{
        PhotoView pv;
        public VH(@NonNull View itemView) {
            super(itemView);
            pv= itemView.findViewById(R.id.pv);
        }
    }

}
