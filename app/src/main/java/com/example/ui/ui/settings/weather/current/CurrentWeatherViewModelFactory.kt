package com.example.ui.ui.settings.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ui.data.provider.UnitProvider
import com.example.ui.data.repository.forecastRepository

class CurrentWeatherViewModelFactory (
     private val forecastRepository: forecastRepository,
     private val unitProvider: UnitProvider
 ) : ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CurrentWeatherViewModel(forecastRepository,unitProvider) as T
    }
}