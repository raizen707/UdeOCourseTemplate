package com.example.ui.data.db

import android.location.Location
import androidx.room.Database
import androidx.room.TypeConverters
import com.example.ui.data.db.entity.CurrentX

@Database(
    entities = [Location::class, CurrentX:: class],
    version = 1
)
@TypeConverters(LocalDataConverter::class)