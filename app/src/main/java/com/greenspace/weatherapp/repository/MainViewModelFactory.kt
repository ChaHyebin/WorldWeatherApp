package com.greenspace.weatherapp.repository

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.greenspace.weatherapp.ui.MainViewModel
import com.greenspace.weatherapp.usecase.WeatherUseCase

class MainViewModelFactory(private val useCase: WeatherUseCase, val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(useCase, application) as T
    }
}