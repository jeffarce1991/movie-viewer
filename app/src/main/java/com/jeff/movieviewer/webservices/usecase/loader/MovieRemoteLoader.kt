package com.jeff.movieviewer.webservices.usecase.loader

import com.jeff.movieviewer.webservices.dto.MovieDto
import com.jeff.movieviewer.webservices.dto.PhotoDto
import com.jeff.movieviewer.webservices.dto.ScheduleDto
import com.jeff.movieviewer.webservices.dto.SeatMapDto
import io.reactivex.Single

interface MovieRemoteLoader {

    fun loadMovie(): Single<MovieDto>
    fun loadSeatMap(): Single<SeatMapDto>
    fun loadSchedule(): Single<ScheduleDto>
}