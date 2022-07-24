package com.greenspace.weatherapp.room

import android.location.Address
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.greenspace.weatherapp.model.WeatherReqs

@Entity
data class City(
    @PrimaryKey(autoGenerate = true) val idx: Int = 0,
    @ColumnInfo(name = "city_name") val cityName: String,
    @ColumnInfo(name = "lat") val lat: Float,
    @ColumnInfo(name = "lon") val lon: Float
) {
    fun toRequest() =
        WeatherReqs(
            cityName = cityName,
            lat = lat,
            lon = lon
        )
}

fun Address.toCity() =
    City(
        cityName = featureName ?:adminArea ?: countryName ?: "",
        lat = latitude.toFloat(),
        lon = longitude.toFloat()
    )