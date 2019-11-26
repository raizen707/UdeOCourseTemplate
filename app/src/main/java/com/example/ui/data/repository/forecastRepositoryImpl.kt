package com.example.ui.data.repository

import androidx.lifecycle.LiveData
import com.example.ui.data.db.CurrentWeatherDao
import com.example.ui.data.db.unitLocalized.current.UnitSpecificCurrentWeatherEntry
import com.example.ui.data.network.WeatherNetworkDataSource
import com.example.ui.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class forecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val  weatherNetworkDataSource: WeatherNetworkDataSource
) : forecastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrrentWeather.observeForever {
            newCurrentWeather -> persistFechetCurrentWeather(newCurrentWeather)
        }
    }

    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {
        return  withContext(Dispatchers.IO){
            return@withContext  currentWeatherDao.getWeatherMetric()
         }
    }
    //inertar a la bd el current weather a travez de dao
    private fun persistFechetCurrentWeather(CurrentWeather : CurrentWeatherResponse){
        GlobalScope.launch ( Dispatchers.IO ){
            currentWeatherDao.upsert(CurrentWeather.current)
        }

    }
}