package com.example.ui.ui.settings.weather.current

import androidx.lifecycle.ViewModel
import com.example.ui.data.provider.UnitProvider
import com.example.ui.data.repository.forecastRepository
import com.example.ui.internal.glide.lazyDeferred
import com.example.ui.ui.settings.base.WeatherViewModel

class CurrentWeatherViewModel(
    private val forecastRepository: forecastRepository,
    unitProvider: UnitProvider
    ) : WeatherViewModel(forecastRepository, unitProvider) {
    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(super.isMetricUnit)
    }
 }
