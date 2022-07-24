package com.greenspace.weatherapp.repository

import com.greenspace.weatherapp.model.WeatherReqs
import com.greenspace.weatherapp.model.WeatherResp
import com.greenspace.weatherapp.room.City
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {
    //api
    fun getWeatherList(request: WeatherReqs): Single<WeatherResp>

    //room
    fun insertCity(city: City): Completable
    fun insertCityList(cityList: List<City>): Completable
    fun deleteCity(cityName: String): Completable
    fun getAllCity(): Single<List<City>>
}