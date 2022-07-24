package com.greenspace.weatherapp.model

import com.greenspace.weatherapp.common.Const
import com.greenspace.weatherapp.room.City

data class WeatherReqs(
    var cityName: String,
    var lat: Float,
    var lon: Float,
    var exclude: String = "current,minutely,hourly,alerts",
    val appid: String = Const.appid
) {
    fun toRequest(): City =
        City(
            cityName = cityName,
            lat = lat,
            lon = lon
        )
}