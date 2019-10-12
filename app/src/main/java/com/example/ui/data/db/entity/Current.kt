package com.example.ui.data.db.entity


import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("current")
    val current: CurrentX,
    @SerializedName("location")
    val location: Location,
    @SerializedName("request")
    val request: Request
)