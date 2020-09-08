package com.jeff.movieviewer.webservices.usecase.loader

import com.jeff.movieviewer.webservices.api.ApiFactory
import com.jeff.movieviewer.webservices.api.photos.MovieApi
import com.jeff.movieviewer.webservices.api.photos.PhotosApi
import com.jeff.movieviewer.webservices.dto.MovieDto
import com.jeff.movieviewer.webservices.dto.PhotoDto
import com.jeff.movieviewer.webservices.dto.ScheduleDto
import com.jeff.movieviewer.webservices.dto.SeatMapDto
import com.jeff.movieviewer.webservices.transformer.ResponseCodeNot200SingleTransformer
import io.reactivex.Single
import javax.inject.Inject

class DefaultMovieRemoteLoader @Inject
constructor(private val apiFactory: ApiFactory): MovieRemoteLoader {

    override fun loadMovie(): Single<MovieDto> {
        return apiFactory.create(MovieApi::class.java)
            .flatMap { it.loadMovie() }
            .compose(ResponseCodeNot200SingleTransformer())
            .flatMap { response ->
                Single.just(response.body()!!) }
    }

    override fun loadSeatMap(): Single<SeatMapDto> {
        return apiFactory.create(MovieApi::class.java)
            .flatMap { it.loadSeatMap() }
            .compose(ResponseCodeNot200SingleTransformer())
            .flatMap { response ->
                Single.just(response.body()!!) }
    }

    override fun loadSchedule(): Single<ScheduleDto> {
        return apiFactory.create(MovieApi::class.java)
            .flatMap { it.loadSchedule() }
            .compose(ResponseCodeNot200SingleTransformer())
            .flatMap { response ->
                Single.just(response.body()!!) }
    }
}