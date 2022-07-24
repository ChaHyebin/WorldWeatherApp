package com.greenspace.weatherapp.api

import com.greenspace.weatherapp.model.WeatherResp
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("onecall")
    fun getWeatherList(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
        @Query("exclude") exclude: String,
        @Query("appid") appid: String
    ): Single<WeatherResp>
}