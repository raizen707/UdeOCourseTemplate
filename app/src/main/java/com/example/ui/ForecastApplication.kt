package com.example.ui

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.example.udeocoursetemplate.R
import com.google.android.gms.location.LocationServices
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*
import org.threeten.bp.LocalDate

//repositorio de inyeccion de dependencia
//Lazy Load, carga perezoza , carga cuando es necesario
//unicamente
class ForecastApplication : Application(),KodeinAware{
    override val kodein = Kodein.lazy {
    //inyeccion
        import(androidXModule(this@ForecastApplication))

        bind() from provider { LocationServices.getFusedLocationProviderClient(instance<Context>()) }

    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        PreferenceManager.setDefaultValues(this, R.xml.preferences,false)
    }
}