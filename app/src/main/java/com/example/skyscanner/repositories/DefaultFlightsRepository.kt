package com.example.skyscanner.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.skyscanner.data.local.FlightsDAO
import com.example.skyscanner.data.local.models.Flights
import com.example.skyscanner.data.remote.ItinerariesResponse
import com.example.skyscanner.data.remote.SkyScannerAPI
import com.example.skyscanner.util.Resource
import javax.inject.Inject

class DefaultFlightsRepository @Inject constructor(
    val dao: FlightsDAO,
    val api: SkyScannerAPI
) : FlightRepository {

    override fun getFlights(): LiveData<List<Flights>> = dao.getAllFlights()

    override suspend fun insertFlights(vararg flights: Flights) {
        dao.upsert(*flights)
    }

    override suspend fun getLatestFlights(): Resource<ItinerariesResponse> {
        return try {
            val response = api.getFlights()
            if (response.isSuccessful) {
                response.body()?.let { flightResponse ->
                    return@let Resource.success(flightResponse)
                } ?: Resource.error("Unknown error - ${response.message()}", null)
            } else {
                return Resource.error("Unknown error - ${response.message()}", null)
            }
        } catch (e: Exception) {
            Log.e(javaClass.name, "getLatestFlights: ${e.message}", )
            Resource.error("Error occured - ${e.message}", null)
        }
    }

}