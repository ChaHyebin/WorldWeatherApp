package com.greenspace.weatherapp.common

import android.text.format.DateFormat
import androidx.room.Room
import com.greenspace.weatherapp.model.WeatherReqs
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun timeStampToDate(time: Long): String {
        var today = Date()
        val noneTimeFormat = SimpleDateFormat("yyyy-MM-dd 00:00:00")
        val strDate = noneTimeFormat.format(time * 1000L)
        val date = noneTimeFormat.parse(strDate)
        val strToday = noneTimeFormat.format(today)
        today = noneTimeFormat.parse(strToday)!!

        val diffSec = (date.time - today.time) / 1000L
        val diffDays = diffSec / (24*60*60)

        return when(diffDays) {
            0L -> "Today"
            1L -> "Tomorrow"
            else -> {
                val simpleDateFormat = SimpleDateFormat("EEE dd MMM", Locale.ENGLISH)
                simpleDateFormat.format(time * 1000L).toString()
            }
        }
    }

    fun getDefaultList() = listOf<WeatherReqs>(
        WeatherReqs("Seoul",37.56f, 126.97f),
        WeatherReqs("London",51.50f, -0.12f),
        WeatherReqs("Chicago",41.88f, -87.62f)
    )
}