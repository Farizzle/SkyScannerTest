package com.example.skyscanner.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.skyscanner.data.local.models.Flights

@Database(entities = [Flights::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FlightsDatabase : RoomDatabase() {
    abstract fun flightDao(): FlightsDAO
}