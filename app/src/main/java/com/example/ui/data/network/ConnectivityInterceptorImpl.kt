package com.example.ui.data.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import java.nio.channels.NoConnectionPendingException

class ConnectivityInterceptorImpl(
    context: Context
) : ConnectivityInterceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isOnline())
            throw NoConnectionPendingException()
        return chain.proceed(chain.request())
    }
    private fun isOnline(): Boolean{
        var connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}