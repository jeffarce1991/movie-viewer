package com.jeff.movieviewer.webservices.dto

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

data class TimesDto (
    @field:SerializedName("parent")
    var parent: String,
    @field:SerializedName("times")
    val times: List<TimeDto>
)

data class TimeDto (
    @field:SerializedName("id")
    var id: String,
    @field:SerializedName("label")
    var label: String,
    @field:SerializedName("popcorn_label")
    val popcornLabel: String,
    @field:SerializedName("popcorn_price")
    val popcornPrice: String,
    @field:SerializedName("price")
    val price: String,
    @field:SerializedName("schedule_id")
    val scheduleId: String,
    @field:SerializedName("seating_type")
    val seatingType: String,
    @field:SerializedName("variant")
    @Nullable
    val variant: String? = null
)