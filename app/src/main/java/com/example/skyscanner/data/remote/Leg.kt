package com.example.skyscanner.data.remote

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

data class Leg(
    val airline_id: String,
    val airline_name: String,
    val arrival_airport: String,
    val arrival_time: String,
    val departure_airport: String,
    val departure_time: String,
    val duration_mins: Int,
    val id: String,
    val stops: Int
) : Serializable {

    fun convertMinutesToDurationString(): String {
        val hours: Int = duration_mins / 60
        val minutes: Int = duration_mins % 60
        return if (hours > 1) {
            String.format("%dh, %dm", hours, minutes)
        } else {
            String.format("%dm", minutes)
        }
    }

    fun flightDetails(): String {
        return String.format("%s-%s, %s", departure_airport, arrival_airport, airline_name)
    }

    fun getAirLineImageUrl(): String {
        return "https://logos.skyscnr.com/images/airlines/small/${airline_id}.png"
    }

    fun getFlightTimes(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
        val calendar = Calendar.getInstance()

        // Get departure time
        calendar.time = formatter.parse(departure_time)
        val departureTimeString = String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE))

        // Get arrival time
        calendar.time = formatter.parse(arrival_time)
        val arrivalTimeString = String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE))

        return "$departureTimeString - $arrivalTimeString"
    }
}