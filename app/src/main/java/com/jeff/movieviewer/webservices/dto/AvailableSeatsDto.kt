package com.jeff.movieviewer.webservices.dto

import com.google.gson.annotations.SerializedName

data class AvailableSeatsDto (
    @field:SerializedName("seats")
    var seats: List<String>,
    @field:SerializedName("seat_count")
    val seatCount: Int
)