package com.example.skyscanner.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.skyscanner.data.remote.Leg

@Entity
data class Flights(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val legs: List<Leg>,
    val price: String,
    val agent: String,
    val agentRating: Double
)