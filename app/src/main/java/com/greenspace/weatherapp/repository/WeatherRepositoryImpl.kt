package com.greenspace.weatherapp.repository

import com.greenspace.weatherapp.api.RetrofitInstance
import com.greenspace.weatherapp.model.WeatherReqs
import com.greenspace.weatherapp.model.WeatherResp
import com.greenspace.weatherapp.room.AppDatabase
import com.greenspace.weatherapp.room.City
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class WeatherRepositoryImpl(private val appDatabase: AppDatabase) : WeatherRepository {
    override fun getWeatherList(request: WeatherReqs): Single<WeatherResp> =
        RetrofitInstance.api.getWeatherList(
            lat = request.lat,
            lon = request.lon,
            exclude = request.exclude,
            appid = request.appid
        )

    override fun insertCityList(cityList: List<City>): Completable =
        appDatabase.cityDao().insertList(cityList)

    override fun insertCity(city: City): Completable =
        appDatabase.cityDao().insert(city)

    override fun deleteCity(cityName: String): Completable =
        appDatabase.cityDao().delete(cityName)

    override fun getAllCity(): Single<List<City>> =
        appDatabase.cityDao().getAll()
}