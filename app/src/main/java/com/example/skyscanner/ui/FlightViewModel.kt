package com.example.skyscanner.ui

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.test.core.app.ApplicationProvider
import com.example.skyscanner.core.SkyScannerApplication
import com.example.skyscanner.data.local.models.Flights
import com.example.skyscanner.data.remote.ItinerariesResponse
import com.example.skyscanner.repositories.FlightRepository
import com.example.skyscanner.util.Event
import com.example.skyscanner.util.Resource
import com.example.skyscanner.util.hasInternetConnection
import kotlinx.coroutines.launch
import java.io.IOException

typealias FlightsEvent = Event<Resource<ItinerariesResponse>>

class FlightViewModel @ViewModelInject constructor(
    val repository: FlightRepository,
    val app: Application = ApplicationProvider.getApplicationContext<SkyScannerApplication>()
) : AndroidViewModel(app) {

    val allFlights = repository.getFlights()

    private val _flightsStatus = MutableLiveData<FlightsEvent>()
    val flightsStatus: LiveData<FlightsEvent>
        get() = _flightsStatus

    init {
        getLatestFlights()
    }

    fun saveFlights(vararg flights: Flights) = viewModelScope.launch {
        repository.insertFlights(*flights)
    }

    fun getLatestFlights() = viewModelScope.launch {
        safeLatestFlights()
    }

    private suspend fun safeLatestFlights() {
        _flightsStatus.value = Event(Resource.loading(null))
        try {
            if (hasInternetConnection(app)) {
                val response = repository.getLatestFlights()
                response.data?.let { safeResponse ->
                    saveFlights(*safeResponse.convertToDatabaseModel())
                }
                _flightsStatus.value = Event(response)
            } else {
                _flightsStatus.postValue(Event(Resource.error("No internet connection")))
                _flightsStatus.postValue(Event(Resource.error("No internet connection")))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _flightsStatus.postValue(Event(Resource.error("Network Failure")))
                else -> _flightsStatus.postValue(Event(Resource.error("Conversion Failure")))
            }
        }
    }

}