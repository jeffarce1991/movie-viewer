package com.jeff.movieviewer.webservices.dto

import com.google.gson.annotations.SerializedName

data class ScheduleDto (
        @field:SerializedName("dates")
        var dates: List<DateDto>,
        @field:SerializedName("cinemas")
        var cinemas: List<CinemasDto>,
        @field:SerializedName("times")
        var times: List<TimesDto>
)