package com.greenspace.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.greenspace.weatherapp.common.Const
import com.greenspace.weatherapp.common.Utils
import com.greenspace.weatherapp.model.WeatherDailyItem
import com.greenspace.weatherapp.databinding.ItemWeatherDetailBinding

class WeatherAdapter :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    var listItem: List<WeatherDailyItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWeatherDetailBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int {
        return if(listItem.size > 6) 6 else listItem.size
    }

    class ViewHolder(val binding: ItemWeatherDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WeatherDailyItem) {
            binding.item = item
            binding.tvWeatherDate.text = Utils.timeStampToDate(item.dt!!)
            val max = item.temp!!.max!!-273.15f
            val min = item.temp!!.min!!-273.15f
            binding.tvTemperature.text = String.format("Max: %.0f°C Min: %.0f°C", max, min)

            val loading = CircularProgressDrawable(itemView.context)
            loading.strokeWidth = 3f
            loading.centerRadius = 10f
            loading.start()

            Glide.with(itemView)
                .load(Const.iconUrl + item.weather!![0].icon + "@2x.png")
                .placeholder(loading)
                .into(binding.ivWeatherIcon)
        }
    }
}