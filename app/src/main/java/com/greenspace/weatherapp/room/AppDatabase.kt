package com.greenspace.weatherapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [City::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun cityDao(): CityDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context:Context): AppDatabase? {
            if(instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "city-db"
                    ).build()
                }
            }
            return instance
        }
    }
}