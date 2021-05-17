package com.example.skyscanner.data.remote

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ItinerariesResponseTest {

    lateinit var itinerariesResponse: ItinerariesResponse

    @Before
    fun setup() {
        itinerariesResponse = FakeData.getItineraries()
    }

    // Need to write a better test... ran out of time
    @Test
    fun convertItinerariesResponse_toFlights() {
        val flights = itinerariesResponse.convertToDatabaseModel()
        val totalFlights = itinerariesResponse.itineraries.size
        var validFlights = 0
        for (flight in flights) {
            if (flight.legs.isNotEmpty()) validFlights++
        }
        assertThat(validFlights).isEqualTo(totalFlights)
    }

}