package com.greenspace.weatherapp.usecase

import com.greenspace.weatherapp.repository.WeatherRepository
import com.greenspace.weatherapp.model.WeatherReqs
import com.greenspace.weatherapp.model.WeatherResp
import com.greenspace.weatherapp.room.City
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class WeatherUseCase(private val weatherRepository: WeatherRepository) {
    fun getWeatherList(request: WeatherReqs): Single<WeatherResp> =
        weatherRepository.getWeatherList(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun insertCity(city: City): Completable =
        weatherRepository.insertCity(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun insertCityList(cityList: List<City>): Completable =
        weatherRepository.insertCityList(cityList)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    fun deleteCity(cityName: String): Completable =
        weatherRepository.deleteCity(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getAllCity(): Single<List<City>> =
        weatherRepository.getAllCity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}