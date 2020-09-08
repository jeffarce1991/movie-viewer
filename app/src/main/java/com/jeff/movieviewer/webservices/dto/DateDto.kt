package com.jeff.movieviewer.webservices.dto

import com.google.gson.annotations.SerializedName

data class DateDto (
    @field:SerializedName("id")
    var id: String,
    @field:SerializedName("label")
    val label: String,
    @field:SerializedName("date")
    val date: String
)