package com.example.skyscanner.data.remote

import java.io.Serializable

data class Itinerary(
    val agent: String,
    val agent_rating: Double,
    val id: String,
    val legs: List<String>,
    val price: String
) : Serializable