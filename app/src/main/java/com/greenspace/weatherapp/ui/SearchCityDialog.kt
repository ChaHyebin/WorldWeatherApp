package com.greenspace.weatherapp.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.location.Geocoder
import android.os.Build
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.greenspace.weatherapp.adapter.SearchCityAdapter
import com.greenspace.weatherapp.common.OnItemClickListener
import com.greenspace.weatherapp.databinding.DialogSearchCityBinding
import com.greenspace.weatherapp.room.City
import com.greenspace.weatherapp.room.toCity
import java.util.*

class SearchCityDialog(context: Context) : Dialog(context) {
    private val binding = DialogSearchCityBinding.inflate(layoutInflater)
    var cityList: List<City> = listOf()
    private var geocoder: Geocoder

    private var confrimClick: (City) -> Unit = {}

    init {
        setContentView(binding.root)

        binding.btnCancel.setOnClickListener { dismiss() }

        binding.btnConfirm.setOnClickListener {
            setCityList()
        }

        geocoder = Geocoder(context, Locale.ENGLISH)
    }

    val searchCityListener = object : OnItemClickListener<City> {
        override fun onItemClick(item: City) {
            Log.w("태그", "터짐")
            confrimClick(item)
            dismiss()
        }
    }

    fun setConfrim(confrim: (City) -> Unit): SearchCityDialog {
        this.confrimClick = confrim
        return this
    }

    fun dialogResize(width: Float, height: Float): SearchCityDialog {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        if (Build.VERSION.SDK_INT < 30){
            val display = windowManager.defaultDisplay
            val size = Point()

            display.getSize(size)

            val x = (size.x * width).toInt()
            val y = (size.y * height).toInt()

            window?.setLayout(x, y)

        }else{
            val rect = windowManager.currentWindowMetrics.bounds

            val x = (rect.width() * width).toInt()
            val y = (rect.height() * height).toInt()

            window?.setLayout(x, y)
        }

        return this
    }

    private fun setCityList() {
        val strCity = binding.etSearchCity.text.toString()
        if(strCity.isEmpty()) {
            Toast.makeText(context, "장소를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }
        val list = geocoder.getFromLocationName(strCity, 15)

        if(list.isNullOrEmpty()) {
            Toast.makeText(context, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show()
        }
        else {
            cityList = list.map { it.toCity() }
            val adapter = binding.rvCityList.adapter as SearchCityAdapter
            adapter.listItem = cityList
            adapter.mListener = searchCityListener
            adapter.notifyDataSetChanged()
        }
    }
}