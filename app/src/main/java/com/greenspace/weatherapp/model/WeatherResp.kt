package com.greenspace.weatherapp.model

data class WeatherResp(
    var cityName: String = "",
    val lat: Float,
    val lon: Float,
    val timezone: String?,
    val timezone_offset: Int?,
    val daily: List<WeatherDailyItem>?
)

data class WeatherDailyItem(
    val dt: Long?,
    val sunrise: Long?,
    val sunset: Long?,
    val moonrise: Long?,
    val moonset: Long?,
    val moon_phase: Float?,
    val temp: WeatherTempItem?,
    val feels_like: WeatherTempItem?,
    val pressure: Int?,
    val humidity: Int?,
    val dew_point: Float?,
    val wind_speed: Float?,
    val wind_deg: Int?,
    val wind_gust: Float?,
    val weather: List<WeatherDetailItem>?,
    val clouds: Int?,
    val pop: Float?,
    val rain: Float?,
    val uvi: Float?
)

data class WeatherTempItem(
    val day: Float?,
    val min: Float?,
    val max: Float?,
    val night: Float?,
    val eve: Float?,
    val morn: Float?,
)

data class WeatherDetailItem(
    val id: Int?,
    val main: String?,
    val description: String?,
    val icon: String?
)