package com.greenspace.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.greenspace.weatherapp.common.OnItemClickListener
import com.greenspace.weatherapp.databinding.ActivityMainBinding
import com.greenspace.weatherapp.model.WeatherResp
import com.greenspace.weatherapp.repository.MainViewModelFactory
import com.greenspace.weatherapp.repository.WeatherRepositoryImpl
import com.greenspace.weatherapp.room.AppDatabase
import com.greenspace.weatherapp.usecase.WeatherUseCase
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val useCase = WeatherUseCase(WeatherRepositoryImpl(AppDatabase.getInstance(this)!!))
        var viewModelFactory = MainViewModelFactory(useCase, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        setContentView(binding.apply {
            activity = this@MainActivity
            viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity
        }.root)

        viewModel.getWeather()

    }

    fun onAddCityWeather() {
        SearchCityDialog(this)
            .dialogResize(0.8f, 0.3f)
            .setConfrim {
                viewModel.addCityData(it)
            }
            .show()
    }

    val removeCityListener = object : OnItemClickListener<WeatherResp> {
        override fun onItemClick(item: WeatherResp) {
            viewModel.removeCityData(item)
        }
    }

    var mBackWait: Long = 0

    override fun onBackPressed() {
        if (System.currentTimeMillis() - mBackWait >= 1000) {
            mBackWait = System.currentTimeMillis()
            Toast.makeText(this, "한번 더 터치하시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        } else {
            finishAndRemoveTask()
            System.runFinalization()
            exitProcess(0)
        }
    }
}