package com.example.skyscanner.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.skyscanner.data.local.models.Flights
import com.example.skyscanner.data.remote.Leg
import com.example.skyscanner.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@HiltAndroidTest
class FlightDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: FlightsDatabase

    @Inject
    @Named("test_dao")
    lateinit var dao: FlightsDAO

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertFlight()  = runBlockingTest {
        val leg1 = Leg(
            "airline_id",
            "airline_name",
            "arrival_airport",
            "arrival_time",
            "departure_airport",
            "depature_time",
            120,
            "leg_id",
            0
        )
        val leg2 = Leg(
            "airline_id",
            "airline_name",
            "arrival_airport",
            "arrival_time",
            "departure_airport",
            "depature_time",
            120,
            "leg_id2",
            0
        )
        val flight = Flights(
            id = "test_id",
            legs = listOf(leg1, leg2),
            price = "£400",
            agent = "agent",
            agentRating = 4.3
        )
        dao.upsert(flight)
        val allFlights = dao.getAllFlights().getOrAwaitValue()
        assertThat(allFlights).contains(flight)
    }

}