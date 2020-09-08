package com.jeff.movieviewer.supplychain.photo

import com.jeff.movieviewer.database.local.Movie
import com.jeff.movieviewer.database.local.Schedule
import com.jeff.movieviewer.database.local.SeatMap
import io.reactivex.Single

interface MovieLoader {

    fun loadMovie(): Single<Movie>
    fun loadSeatMap(): Single<SeatMap>
    fun loadSchedule(): Single<Schedule>
}