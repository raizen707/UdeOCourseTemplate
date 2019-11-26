package com.example.ui.ui.settings.base

import androidx.lifecycle.ViewModel
import com.example.ui.data.provider.UnitProvider
import com.example.ui.data.repository.forecastRepository
import com.example.ui.internal.glide.UnitSystem
import com.example.ui.internal.glide.lazyDeferred
import java.nio.channels.spi.AsynchronousChannelProvider

abstract class WeatherViewModel(
    private val forecastRepository: forecastRepository ,
    unitProvider: UnitProvider
) : ViewModel(){

    private val unitSystem = unitProvider.getUnitSystem()

    val isMetricUnit : Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weatherLocation by lazyDeferred {
        forecastRepository.getCurrentWeather(true)
    }
}