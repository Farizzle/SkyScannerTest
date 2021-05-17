package com.example.skyscanner.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.skyscanner.data.local.models.Flights
import com.example.skyscanner.data.remote.ItinerariesResponse
import com.example.skyscanner.util.Resource

class FakeFlightsRepository(private val shouldReturnNetworkError: Boolean) : FlightRepository {

    private val flights = mutableListOf<Flights>()

    private val observableFlights = MutableLiveData<List<Flights>>(flights)

    private fun refreshLiveData() {
        observableFlights.postValue(flights)
    }

    override fun getFlights(): LiveData<List<Flights>> {
        return observableFlights
    }

    override suspend fun insertFlights(vararg flights: Flights) {
        this.flights.addAll(flights)
        refreshLiveData()
    }

    override suspend fun getLatestFlights(): Resource<ItinerariesResponse> {
        return if (shouldReturnNetworkError) {
            Resource.error("Network error")
        } else {
            Resource.success(
                ItinerariesResponse(
                    // itineraries
                    listOf(),
                    // legs
                    listOf()
                )
            )
        }
    }

}