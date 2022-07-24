package com.greenspace.weatherapp.room

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface CityDao {
    @Query("SELECT * FROM city")
    fun getAll(): Single<List<City>>

    @Insert
    fun insert(city: City): Completable

    @Insert
    @JvmSuppressWildcards
    fun insertList(cityList: List<City>): Completable

    @Query("DELETE FROM city WHERE city_name = :cityName")
    fun delete(cityName: String): Completable
}