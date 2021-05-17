package com.example.skyscanner.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.skyscanner.R
import com.example.skyscanner.data.local.models.Flights
import com.example.skyscanner.data.remote.Leg
import kotlinx.android.synthetic.main.list_item_flights.view.*
import javax.inject.Inject

class FlightAdapter @Inject constructor(
    val glide : RequestManager
) : ListAdapter<Flights, FlightAdapter.FlightViewHolder>(FlightDiffCheck()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FlightViewHolder(layoutInflater.inflate(R.layout.list_item_flights, parent, false))
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FlightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(flight: Flights) {
            itemView.apply {
                configureSharedContent(flight, this)
                configureOutboundContent(flight.legs.first(), this)
                configureInboundContent(flight.legs.last(), this)
            }
        }

        private fun configureSharedContent(flight: Flights, itemView: View){
            itemView.flight_price.text = flight.price
            itemView.flight_price_provider.text = "via ${flight.agent}"
        }

        private fun configureOutboundContent(leg: Leg, itemView: View){
            if(leg.stops == 0) {
                itemView.outbound_path.text = "Direct"
            } else {
                if (leg.stops == 1) {
                    itemView.outbound_path.text = "1 Stop"
                } else {
                    itemView.outbound_path.text = "${leg.stops} stops"
                }
            }
            itemView.outbound_duration.text = leg.convertMinutesToDurationString()
            itemView.outbound_time.text = leg.getFlightTimes()
            itemView.outbound_details.text = leg.flightDetails()
            glide.load(leg.getAirLineImageUrl()).into(itemView.outbound_airline_image)
        }

        private fun configureInboundContent(leg: Leg, itemView: View) {
            if(leg.stops == 0) {
                itemView.inbound_path.text = "Direct"
            } else {
                if (leg.stops == 1) {
                    itemView.inbound_path.text = "1 Stop"
                } else {
                    itemView.inbound_path.text = "${leg.stops} stops"
                }
            }
            itemView.inbound_duration.text = leg.convertMinutesToDurationString()
            itemView.inbound_time.text = leg.getFlightTimes()
            itemView.inbound_details.text = leg.flightDetails()
            glide.load(leg.getAirLineImageUrl()).into(itemView.inbound_airline_image)
        }

    }

    class FlightDiffCheck : DiffUtil.ItemCallback<Flights>() {
        override fun areItemsTheSame(oldItem: Flights, newItem: Flights): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Flights, newItem: Flights): Boolean {
            return oldItem == newItem
        }
    }

}