package com.greenspace.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenspace.weatherapp.common.OnItemClickListener
import com.greenspace.weatherapp.databinding.ItemCityListBinding
import com.greenspace.weatherapp.room.City

class SearchCityAdapter :
    RecyclerView.Adapter<SearchCityAdapter.ViewHolder>() {
    var listItem: List<City> = listOf()
    var mListener: OnItemClickListener<City>? = null
//    var mListener:View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCityListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    inner class ViewHolder(val binding: ItemCityListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: City) {
            binding.item = item
            binding.itemClickListener = mListener

            binding.executePendingBindings()
        }
    }
}