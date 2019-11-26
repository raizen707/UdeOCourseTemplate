package com.example.ui.data.db

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

object LocalDataConverter{
    @TypeConverter
    @JvmStatic
    fun stringToDate(str: String?) = str?.let {
        LocalDate.parse(it, DateTimeFormatter.ISO_LOCAL_DATE)
    }
    @TypeConverter
    @JvmStatic
    fun dataToString(dateTime : LocalDate?) = dateTime?.format([DateTimeFormatter.ISO_LOCAL_DATE])
}