package com.mrhi2023.ex99jetpacklivedata

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mrhi2023.ex99jetpacklivedata.databinding.RecylerItemBinding

class RecyclerItemAdapter(val items:List<Item>) : Adapter<RecyclerItemAdapter.VH>() {

    inner class VH(val binding:RecylerItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding:RecylerItemBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.recyler_item, parent, false)
        return VH(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.item = items[position]
    }
}