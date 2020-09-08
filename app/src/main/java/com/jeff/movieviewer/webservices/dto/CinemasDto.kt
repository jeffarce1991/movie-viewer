package com.jeff.movieviewer.webservices.dto

import com.google.gson.annotations.SerializedName

data class CinemasDto (
    @field:SerializedName("parent")
    var parent: String,
    @field:SerializedName("cinemas")
    val cinemas: List<CinemaDto>
)

data class CinemaDto (
    @field:SerializedName("id")
    var id: String,
    @field:SerializedName("cinema_id")
    var cinemaId: String,
    @field:SerializedName("label")
    val label: String
)