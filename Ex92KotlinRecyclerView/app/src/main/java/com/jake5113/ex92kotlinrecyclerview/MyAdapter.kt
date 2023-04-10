package com.jake5113.ex92kotlinrecyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

class MyAdapter constructor(var context: Context, var items: MutableList<Item>) :
    Adapter<MyAdapter.VH>() {
    inner class VH(itemView: View) : ViewHolder(itemView) {
        val tvName: TextView by lazy { itemView.findViewById(R.id.tv_name) }
        val tvMsg: TextView by lazy { itemView.findViewById(R.id.tv_msg) }
        val iv: ImageView by lazy { itemView.findViewById(R.id.iv) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var itemView: View =
            LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false)
        return VH(itemView)
    }

    // return 실행문을 = (할당연산자)로 단순화
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        //var item: Item = items.get(position)
        // 코틀린은 리스트의 get() 대신에 배열처럼 [] 를 권장함
        var item: Item = items[position]

        holder.tvName.setText(item.name)
        // 코틀린은 getXXX(), setXXX() 를 권하지 않음
        holder.tvMsg.text = item.msg

        Glide.with(context).load(item.imgId).into(holder.iv)

        // 아이템뷰를 클릭 했을때 화면 이동
        holder.itemView.setOnClickListener {
            val intent: Intent = Intent(context, ItemDetailActivity::class.java)
            intent.putExtra("name", item.name)
            intent.putExtra("msg", item.msg)
            intent.putExtra("imgId", item.imgId)

            // 액티비티 전환시에 뷰들에 효과 주기 . ActivityOptions
            val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context as MainActivity,
                Pair(holder.iv, "iii")
            )
            context.startActivity(intent, options.toBundle())
        }
    }
}