package com.jeff.movieviewer.webservices.dto

import com.google.gson.annotations.SerializedName

data class SeatMapDto (
        @field:SerializedName("seatmap")
        var seatmap: List<List<String>>,
        @field:SerializedName("available")
        val availableSeats: AvailableSeatsDto
)