package com.example.ui.data.network

import com.example.ui.data.network.response.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import  okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//retrofit
//es un mini framework para consumo de APIS

//key
const val API_KEY = "b15658c8de64d2721bedb5e0cabd452f"
const val urlGateway ="http://api.weatherstack.com/"
//URL http://api.weatherstack.com/current?access_key=b15658c8de64d2721bedb5e0cabd452f&query=New%20York
interface ApixuWeatherApiService {
    //Metadata, con un data annotation,
    //REST= GET, POST, PUT == Editar,
    //DELETE, PATCH (Update a medias)
    @GET("current")
    fun getCurrentWeather(
        @Query("query") location  : String,
        @Query("lang") lang : String
    ): Deferred<CurrentWeatherResponse>
    //metodo estatico para llamar o invocar el api

    companion object{
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ApixuWeatherApiService{
            //StringBuilder
            //o variable iteradora de texto
            val requestInterceptor = Interceptor {
                 chain ->
                val url = chain.request().url().newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()
                val request = chain.request().newBuilder()
                    .url(url).build()

                return@Interceptor chain.proceed(request)
            }
            //Coroutine == llamada asincronas
            //levatamieto de threads
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()
            return  Retrofit.Builder().client(okHttpClient)
                .baseUrl("http://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApixuWeatherApiService::class.java)
        }

    }



}