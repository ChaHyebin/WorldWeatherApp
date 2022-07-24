package com.greenspace.weatherapp.ui

import android.app.Application
import androidx.lifecycle.*
import com.greenspace.weatherapp.common.Utils
import com.greenspace.weatherapp.model.WeatherReqs
import com.greenspace.weatherapp.model.WeatherResp
import com.greenspace.weatherapp.room.City
import com.greenspace.weatherapp.usecase.WeatherUseCase

class MainViewModel(private val weatherUseCase: WeatherUseCase, application: Application) : AndroidViewModel(application) {
    var weatherList: MutableLiveData<List<WeatherResp>> = MutableLiveData(listOf())
    var weatherTmpList = ArrayList<WeatherResp>()

    fun getWeather() {
        var request:ArrayList<WeatherReqs> = arrayListOf()
        weatherUseCase.getAllCity()
            .subscribe({ list->
                if(list.isEmpty()) {
                    request = ArrayList(Utils.getDefaultList())
                    weatherUseCase.insertCityList(request.map { it.toRequest() })
                        .subscribe()
                }
                else {
                    request = ArrayList(list.map { it.toRequest() })
                }
                getWeatherData(request)

            }, {
                request = ArrayList(Utils.getDefaultList())
                weatherUseCase.insertCityList(request.map { it.toRequest() })
                    .subscribe()
                getWeatherData(request)
                it.printStackTrace()
            })
    }

    fun getWeatherData(request: List<WeatherReqs>) {
        weatherTmpList.clear()
        request.forEach { reqs ->
            weatherUseCase.getWeatherList(reqs)
                .subscribe({
                    it.cityName = reqs.cityName
                    weatherTmpList.add(it)
                    weatherList.value = weatherTmpList
                }, {
                    it.printStackTrace()
                })
        }
    }

    fun addCityData(city: City) {
        weatherUseCase.insertCity(city)
            .subscribe({
                getWeather()
            }, {
                it.printStackTrace()
            })
    }

    fun removeCityData(item: WeatherResp) {
        weatherUseCase.deleteCity(item.cityName)
            .subscribe({
                weatherTmpList.remove(item)
                weatherList.value = weatherTmpList
            }, {
                it.printStackTrace()
            })
    }
}