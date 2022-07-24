package com.greenspace.weatherapp.api

import com.google.gson.GsonBuilder
import com.greenspace.weatherapp.common.Const
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Const.weatherUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    val api : WeatherApi by lazy {
        retrofit.create(WeatherApi::class.java)
    }
}