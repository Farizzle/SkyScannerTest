package com.example.skyscanner.repositories

import androidx.lifecycle.LiveData
import com.example.skyscanner.data.local.models.Flights
import com.example.skyscanner.data.remote.ItinerariesResponse
import com.example.skyscanner.util.Resource

interface FlightRepository {
    fun getFlights(): LiveData<List<Flights>>
    suspend fun insertFlights(vararg flights: Flights)
    suspend fun getLatestFlights() : Resource<ItinerariesResponse>
}