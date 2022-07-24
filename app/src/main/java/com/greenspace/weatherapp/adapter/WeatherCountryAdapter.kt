package com.greenspace.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenspace.weatherapp.common.OnItemClickListener
import com.greenspace.weatherapp.model.WeatherResp
import com.greenspace.weatherapp.databinding.ItemWeatherCountryBinding

class WeatherCountryAdapter :
    RecyclerView.Adapter<WeatherCountryAdapter.ViewHolder>() {
    var countryList: List<WeatherResp> = listOf()
    var mListener: OnItemClickListener<WeatherResp>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWeatherCountryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countryList[position])
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    inner class ViewHolder(val binding: ItemWeatherCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(countryItem: WeatherResp) {
            binding.item = countryItem

            binding.itemClickListener = mListener
            binding.executePendingBindings()
        }
    }
}