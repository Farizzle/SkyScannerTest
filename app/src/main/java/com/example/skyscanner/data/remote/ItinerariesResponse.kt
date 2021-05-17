package com.example.skyscanner.data.remote

import com.example.skyscanner.data.local.models.Flights

data class ItinerariesResponse(
    val itineraries: List<Itinerary>,
    val legs: List<Leg>
) {
    fun convertToDatabaseModel() : Array<Flights> {
        val flights = mutableListOf<Flights>()
        for (itinerary in itineraries) {
            val listOfLegs = mutableListOf<Leg>()
            for (itineraryLeg in itinerary.legs){
                listOfLegs.add(legs.getCorrectLeg(itineraryLeg))
            }
            val flightToAdd = Flights(
                id = itinerary.id,
                legs = listOfLegs,
                price = itinerary.price,
                agent = itinerary.agent,
                agentRating = itinerary.agent_rating
            )
            flights.add(flightToAdd)
        }
        return flights.toTypedArray()
    }

}

fun List<Leg>.getCorrectLeg(legId: String) : Leg {
    return this.first { it.id == legId }
}


