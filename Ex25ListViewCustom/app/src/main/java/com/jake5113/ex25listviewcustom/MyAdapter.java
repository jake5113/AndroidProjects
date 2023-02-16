package com.jake5113.ex25listviewcustom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<Item> items;

    // 생성자
    public MyAdapter(Context context ,ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // 리스트뷰가 보여줄 아이템 1개의 View 객체를 생성하여 리턴해주는 메소드!
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. create view : xml모양으로 View 객체를 생성

        // 혹시 재활용할 view가 없는가? - 이 메소드의 두번째 파라미터 view
        if (convertView == null) {
            // xml 파일을 읽어서 View 객체로 만들어주는 객체를 운영체제로부터 소환 : LayoutInflater
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.listview_item, null);
        }

        // 2. bind view : 생성된 View 객체안에 정보 값들을 설정(연결)
        // 이 메소드의 첫번째 파라미터 position - 현재 만들어야 할 번째 인덱스

        // 현재번째 데이터(Item객체) 얻어오기
        Item item = items.get(position);

        // 아이템뷰 안에 있는 자식뷰들을 참조하기
        ImageView iv = convertView.findViewById(R.id.iv);
        TextView tvName = convertView.findViewById(R.id.tv_name);
        TextView tvNation = convertView.findViewById(R.id.tv_nation);

        // 각 뷰들에 현재번째 데이터를 연결(설정)
        tvName.setText(item.name);
        tvNation.setText(item.nation);
        iv.setImageResource(item.imgId);

        return convertView; // 리스트뷰가 이 리턴된 뷰를 화면에 목록으로 추가해줌
    }
}
