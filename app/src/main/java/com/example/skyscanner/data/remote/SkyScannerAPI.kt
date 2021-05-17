package com.example.skyscanner.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface SkyScannerAPI {

    @GET("flights.json")
    suspend fun getFlights(): Response<ItinerariesResponse>

}