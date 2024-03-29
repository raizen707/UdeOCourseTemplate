package com.example.ui.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ui.data.db.entity.CURRENT_WEATHER_ID
import com.example.ui.data.db.entity.CurrentX
import com.example.ui.data.db.unitLocalized.current.UnitSpecificCurrentWeatherEntry

@Dao
interface CurrentWeatherDao{
    //insert in SQLite with ROOM
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentX)

    @Query(value = "SELECT * FROM current_weather WHERE id = $CURRENT_WEATHER_ID" )
    fun getWeatherMetric() : LiveData<UnitSpecificCurrentWeatherEntry>

    @Query(value = "SELECT * FROM current_weather")
    fun getAll()

}