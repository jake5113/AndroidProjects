package com.mrhi2023.ex98databindingbindingadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mrhi2023.ex98databindingbindingadapter.databinding.RecyclerItemBinding

class RecyclerItemAdapter(val context: Context, val items:List<Item>) : Adapter<RecyclerItemAdapter.VH>() {

    inner class VH(val binding:RecyclerItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding:RecyclerItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.recycler_item, parent, false)
        return VH(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        // 데이터 바인딩 되어 있기에 xml에 선언한 변수 item에 객체값만 설정해주면 알아서 모든 뷰들에 바인딩 됨
        holder.binding.item= items[position]
        
        //아이템클릭 이벤트 처리 -- 뷰 바인딩..
        holder.binding.root.setOnClickListener {
            Toast.makeText(context, "${items[position].title}", Toast.LENGTH_SHORT).show()
        }
    }
}