package com.example.ui.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ui.internal.NoConnectivityException
import com.example.ui.data.network.response.CurrentWeatherResponse

class WeatherNetworkDataSourceImpl(
    private var apixuWeatherApiService: ApixuWeatherApiService
) : WeatherNetworkDataSource {
    private val _downloaderCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrrentWeather: LiveData<CurrentWeatherResponse>
        get() =  _downloaderCurrentWeather

    override suspend fun fetchCurrentWeather(location: String, languageCode: String) {
        try {
            val fetchedCurrentWeather = apixuWeatherApiService
                .getCurrentWeather(location, languageCode)
                .await()
            _downloaderCurrentWeather.postValue(fetchedCurrentWeather)

        }
        catch (e : Exception){
            Log.e("Connectivity","Theres's no internet connection",e)
        }
    }
}