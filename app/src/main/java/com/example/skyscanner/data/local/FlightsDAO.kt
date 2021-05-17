package com.example.skyscanner.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.skyscanner.data.local.models.Flights

@Dao
interface FlightsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg flights: Flights)

    @Query("SELECT * FROM flights")
    fun getAllFlights(): LiveData<List<Flights>>

}