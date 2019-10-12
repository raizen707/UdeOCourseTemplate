package com.example.ui

import android.annotation.SuppressLint
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest

//ACTUALIZAR LOS PERMISOS USANDO LIFECYCLE
//OBSERVABLE
class LifeCycleBoundLocationManager(
    LifecyclwOwner : LifecycleOwner,
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val locationCallback: LocationCallback
) : LifecycleObserver{

    //agregando observador
    init {
        LifecyclwOwner.lifecycle.addObserver(this)
    }
    //SIMILAR A UN CALL BACK FUNCTION EN JS
    private val locationRequest = LocationRequest().apply {
        interval = 5000
        fastestInterval = 5000
        priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
    }
    //DATA ANNOTATION INICIAR LA LOCALIZACION
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @SuppressLint("MissingPermission")
    fun startLocationUpdates(){
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback,null)
    }
    //REMOVER

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun removeLocationUpdates(){
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }
}
