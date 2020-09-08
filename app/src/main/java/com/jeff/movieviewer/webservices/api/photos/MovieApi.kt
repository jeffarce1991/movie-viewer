package com.jeff.movieviewer.webservices.api.photos

import com.jeff.movieviewer.webservices.dto.MovieDto
import com.jeff.movieviewer.webservices.dto.ScheduleDto
import com.jeff.movieviewer.webservices.dto.SeatMapDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {

    @GET("/movie.json")
    fun loadMovie(): Single<Response<MovieDto>>

    @GET("/seatmap.json")
    fun loadSeatMap(): Single<Response<SeatMapDto>>

    @GET("/schedule.json")
    fun loadSchedule(): Single<Response<ScheduleDto>>

/*    @GET("photos/{id}")
    fun loadPhotoById(@Path("id") id: Int): Single<Response<PhotoDto>>*/
}