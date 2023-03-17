package com.jake5113.ex90firebasechatting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.VH> {

    Context context;
    ArrayList<MessageItem> messageItems;

    final int TYPE_MY = 0, TYPE_OTHER = 1;

    public MessageAdapter() {

    }

    public MessageAdapter(Context context, ArrayList<MessageItem> messageItems) {
        this.context = context;
        this.messageItems = messageItems;
    }

    // 리사이클러뷰의 항목뷰가 경우에 따라 다른 모양으로 보여야 할 때 사용하는 콜백메소드
    // 이 메소드에 해당 position 에 따른 식별값(ViewType 번호)을 정하여 리턴하면
    // 그 값이 onCreateViewHolder() 메소드의 두번째 파라미터에 전달됨.
    // onCreateViewHolder() 메소드 안에서 그 값에 따라 다른 xml 문서를 inflate하면 됨.
    @Override
    public int getItemViewType(int position) {
        if (messageItems.get(position).name.equals(G.nickName)) {
            return TYPE_MY;
        } else {
            return TYPE_OTHER;
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = null;
        if (viewType == TYPE_MY) {
            itemView = LayoutInflater.from(context).inflate(R.layout.my_messagebox, parent, false);
        } else
            itemView = LayoutInflater.from(context).inflate(R.layout.other_messagebox, parent, false);

        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        MessageItem item = messageItems.get(position);
        holder.tvName.setText(item.name);
        holder.tvMsg.setText(item.message);
        holder.tvTime.setText(item.time);
        Glide.with(context).load(item.profileUrl).into(holder.civ);

    }

    @Override
    public int getItemCount() {
        return messageItems.size();
    }

    class VH extends RecyclerView.ViewHolder {
        // 메세지 타입에 따라 뷰가 다르기에 바인딩 클래스를 고정하지 못함
        // [MyMessageboxBinding, OtherMessageboxBinding]
        // ViewHolder 를 2개 만들어 사용하기도 함 [MyVH, OtherVH]
        // 홀더를 2개 만들면 onBinding 에서도 분기처리가 필요하여
        // 이번에는 뷰바인딩을 안쓰고 제작해보기
        CircleImageView civ;
        TextView tvName, tvMsg, tvTime;

        public VH(@NonNull View itemView) {
            super(itemView);
            civ = itemView.findViewById(R.id.civ);
            tvName = itemView.findViewById(R.id.tv_name);
            tvMsg = itemView.findViewById(R.id.tv_msg);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }
}


