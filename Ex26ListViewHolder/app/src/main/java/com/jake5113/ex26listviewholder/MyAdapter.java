package com.jake5113.ex26listviewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> items;

    public MyAdapter(Context context, ArrayList<String> items) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. create view
        // 재활용할 View가 있는지 확인
        if (convertView == null) {
            // listview_item.xml 문서를 읽어와서 View 객체로 생성해주는 inflater 객체를 소환
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);


            // 만들어진 뷰(view) 안에 있는 자식
            ViewHolder holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        // 2. bind view
        // 현재 보여줄 데이터를 얻어오기
        String item = items.get(position);

        // 아이템뷰안에 tag로 저장되어 있던 ViewHolder 객체를 빼오기
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.tv.setText(item);

        return convertView;
    }

    // 항목 1개의 뷰(item view) 안에 있는 자식뷰들 참조변수를 멤버로 가지는 이너 클래스
    class ViewHolder {
        TextView tv;

        public ViewHolder(View itemView) {
            tv = itemView.findViewById(R.id.tv);
        }

    }

}
