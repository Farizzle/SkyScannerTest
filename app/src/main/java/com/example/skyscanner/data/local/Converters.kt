package com.example.skyscanner.data.local

import androidx.room.TypeConverter
import com.example.skyscanner.data.remote.Leg
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    val gson = Gson()

    @TypeConverter
    fun legsToString(legs: List<Leg?>?): String {
        return gson.toJson(legs)
    }

    @TypeConverter
    fun jsonToLegs(json: String): List<Leg?>? {
        val legsType = object : TypeToken<List<Leg?>?>() {}.type
        return gson.fromJson(json, legsType)
    }

}