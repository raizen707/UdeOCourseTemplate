package com.example.ui

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.udeocoursetemplate.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

private const val MY_PERMISSION_ACCESS_COARSE_LOCATION =1
class MainActivity : AppCompatActivity() {
//LAZY LOAD VARIABLE
    private  lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        bottom_nav.setupWithNavController(navController)

        NavigationUI.setupActionBarWithNavController(this,navController)
        if (hasLoctaionPermission()){

        }else{
            requestLocaionPermission()
        }
    }

    private fun bindLocationManager(){
        //LifeCycleBoundLocationManager()
    }

    override fun onSupportNavigateUp(): Boolean {
     return NavigationUI.navigateUp(navController,null)
    }
    private fun requestLocaionPermission(){
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
            MY_PERMISSION_ACCESS_COARSE_LOCATION
        )
    }
    private fun hasLoctaionPermission(): Boolean{
        return ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.ACCESS_COARSE_LOCATION)  == PackageManager.PERMISSION_GRANTED
    }

}
