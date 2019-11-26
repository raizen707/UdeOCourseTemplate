package com.example.ui.data.network

import androidx.lifecycle.LiveData
import com.example.ui.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    //two way binding
    //llena una propiedad en dos vias
    //del back and front y viceversa
    val downloadedCurrrentWeather : LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        languageCode: String
    )
}