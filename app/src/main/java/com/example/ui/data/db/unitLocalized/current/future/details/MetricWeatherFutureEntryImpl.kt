package com.example.ui.data.db.unitLocalized.current

import androidx.room.ColumnInfo

class MetricWeatherFutureEntryImpl(
    @ColumnInfo(name = "temperature")
    override val temperature: Double,
    @ColumnInfo(name = "feelslike")
    override val feelslike: Double,
    @ColumnInfo(name = "visibility")
    override val visibility: Double
) : UnitSpecificCurrentFutureWeatherEntry