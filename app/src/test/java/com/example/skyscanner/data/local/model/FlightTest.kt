package com.example.skyscanner.data.local.model

import com.example.skyscanner.data.local.models.Flights
import com.example.skyscanner.data.remote.Leg
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FlightTest {

    lateinit var flight: Flights

    @Before
    fun setup() {
        val leg1 = Leg(
            "WZ",
            "Wizz Air",
            "LTN",
            "2020-10-31T17:00",
            "BUD",
            "2020-10-31T15:35",
            145,
            "leg_id",
            0
        )
        val leg2 = Leg(
            "WZ",
            "Wizz Air",
            "BUD",
            "2020-11-11T21:10",
            "LTN",
            "2020-11-11T19:45",
            145,
            "leg_4",
            0
        )
        flight = Flights(
            id = "it_1",
            legs = listOf(leg1, leg2),
            price = "£35",
            agent = "Wizzair.com",
            agentRating = 9.1
        )
    }

    @Test
    fun `convert minutes to correct time string, returns true`() {
        val expectedOutput = "2h, 25m"
        val flightTimeString = flight.legs.first().convertMinutesToDurationString()
        assertThat(flightTimeString).isEqualTo(expectedOutput)
    }

    @Test
    fun `flight details provides correct string, returns true`() {
        // [DEPART-AIRPORT]-[ARRIVE-AIRPORT], [AIRLINE_NAME]
        val expectOutput = "BUD-LTN, Wizz Air"
        val flightDetailsString = flight.legs.first().flightDetails()
        assertThat(flightDetailsString).isEqualTo(expectOutput)
    }

    @Test
    fun `flight time provides duration added to depature time, returns true`() {
        val expectedOutput = "15:35 - 17:00"
        val flightTimesString = flight.legs.first().getFlightTimes()
        assertThat(flightTimesString).isEqualTo(expectedOutput)
    }

}