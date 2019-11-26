package com.example.ui.ui.settings.weather.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.udeocoursetemplate.R
import com.example.ui.data.network.ApixuWeatherApiService
import com.example.ui.data.network.ConnectivityInterceptorImpl
import com.example.ui.data.network.WeatherNetworkDataSource
import com.example.ui.data.network.WeatherNetworkDataSourceImpl
import com.example.ui.ui.settings.base.ScopedFragment
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CurrentWeather : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private  val viewModelFactory: CurrentWeatherViewModelFactory  by instance()
    private lateinit var viewModel: CurrentWeatherViewModel

    companion object {
        fun newInstance() = CurrentWeather()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentWeatherViewModel::class.java)
        //SOLO PARA DEMO, NO SE HACE EN PRODUCCION
        val apiService = ApixuWeatherApiService(ConnectivityInterceptorImpl(this.context!!))
        val weatherNetworkDataSource = WeatherNetworkDataSourceImpl(apiService)
        weatherNetworkDataSource
            .downloadedCurrrentWeather
            .observe(this, Observer {
                textView.text = it.toString()
            })
        GlobalScope.launch(Dispatchers.Main) {
            val currentWeatherResponse = apiService
                .getCurrentWeather("Guatemala","en")
                .await()
            weatherNetworkDataSource.fetchCurrentWeather("London","en")
        }
    }

}
