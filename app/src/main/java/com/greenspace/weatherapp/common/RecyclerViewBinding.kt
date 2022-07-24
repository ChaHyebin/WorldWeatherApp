package com.greenspace.weatherapp.common

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greenspace.weatherapp.model.WeatherDailyItem
import com.greenspace.weatherapp.model.WeatherResp
import com.greenspace.weatherapp.adapter.SearchCityAdapter
import com.greenspace.weatherapp.adapter.WeatherAdapter
import com.greenspace.weatherapp.adapter.WeatherCountryAdapter
import com.greenspace.weatherapp.room.City

object RecyclerViewBinding {
    @BindingAdapter("listItem", requireAll = false)
    @JvmStatic
    fun setWeatherItem(recyclerView: RecyclerView,
        listItem: List<WeatherDailyItem>?
    ) {
        if(recyclerView.adapter == null) {
            recyclerView.adapter = WeatherAdapter()
        }

        val newAdapter = recyclerView.adapter as WeatherAdapter
        newAdapter.listItem = listItem ?: listOf()
        newAdapter.notifyDataSetChanged()
    }

    @BindingAdapter("countryList", "itemClickListener", requireAll = false)
    @JvmStatic
    fun setCountryItem(
        recyclerView: RecyclerView,
        countryList: List<WeatherResp>?,
        itemClickListener: OnItemClickListener<WeatherResp>
    ) {
        if(recyclerView.adapter == null) {
            recyclerView.adapter = WeatherCountryAdapter()
        }

        val newAdapter = recyclerView.adapter as WeatherCountryAdapter
        newAdapter.mListener = itemClickListener
        newAdapter.countryList = countryList ?: listOf()
        newAdapter.notifyDataSetChanged()
    }

    @BindingAdapter("cityList", "itemClickListener", requireAll = false)
    @JvmStatic
    fun setCityItem(
        recyclerView: RecyclerView,
        cityList: List<City>?,
        itemClickListener: OnItemClickListener<City>?
    ) {
        if(recyclerView.adapter == null) {
            recyclerView.adapter = SearchCityAdapter()
        }

        val newAdapter = recyclerView.adapter as SearchCityAdapter
        newAdapter.mListener = itemClickListener
        newAdapter.listItem = cityList ?: listOf()
        newAdapter.notifyDataSetChanged()
    }
}