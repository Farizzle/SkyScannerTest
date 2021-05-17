package com.example.skyscanner.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.skyscanner.MainCoroutineRule
import com.example.skyscanner.data.local.models.Flights
import com.example.skyscanner.getOrAwaitValueTest
import com.example.skyscanner.repositories.FakeFlightsRepository
import com.example.skyscanner.util.Resource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class FlightsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: FlightViewModel

    @Before
    fun setup() {
        viewModel = FlightViewModel(FakeFlightsRepository(shouldReturnNetworkError = false))
    }

    @Test
    fun `insert product items, returns true`() {
        val fakeFlight = Mockito.mock(Flights::class.java)
        viewModel.saveFlights(fakeFlight)
        val value = viewModel.allFlights.getOrAwaitValueTest()
        assertThat(value).contains(fakeFlight)
    }

    @Test
    fun `valid network fetch for latest products provides success, returns true`() =
        runBlockingTest {
            viewModel = FlightViewModel(FakeFlightsRepository(shouldReturnNetworkError = false))
            viewModel.getLatestFlights()
            val value = viewModel.flightsStatus.getOrAwaitValueTest()
            assertThat(value.getContentIfNotHandled()!!.status).isEqualTo(Resource.Status.SUCCESS)
        }

    @Test
    fun `invalid network fetch for latest products provides error, returns true`() =
        runBlockingTest {
            viewModel = FlightViewModel(FakeFlightsRepository(shouldReturnNetworkError = true))
            viewModel.getLatestFlights()
            val value = viewModel.flightsStatus.getOrAwaitValueTest()
            assertThat(value.getContentIfNotHandled()!!.status).isEqualTo(Resource.Status.ERROR)
        }


}