package com.example.ui.data.repository

import androidx.lifecycle.LiveData
import com.example.ui.data.db.unitLocalized.current.UnitSpecificCurrentWeatherEntry

interface forecastRepository {
    //se levanta en un thread
    //y se puede llamar , pausar
    //detener y continuar
    suspend fun getCurrentWeather(metric: Boolean) : LiveData<out UnitSpecificCurrentWeatherEntry>
}