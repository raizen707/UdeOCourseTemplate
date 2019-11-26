package com.example.ui.data.network.response


import com.example.ui.data.db.entity.CurrentX
import com.example.ui.data.db.entity.Location
import com.example.ui.data.db.entity.Request
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("current")
    val current: CurrentX,
    @SerializedName("location")
    val location: Location,
    @SerializedName("request")
    val request: Request
)