package com.jeff.movieviewer.supplychain.photo

import com.jeff.movieviewer.database.local.*
import com.jeff.movieviewer.webservices.dto.*
import com.jeff.movieviewer.webservices.internet.RxInternet
import com.jeff.movieviewer.webservices.usecase.loader.MovieRemoteLoader
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class DefaultMovieLoader @Inject
constructor(private val remoteLoader: MovieRemoteLoader,
            private val rxInternet: RxInternet): MovieLoader{

    override fun loadMovie(): Single<Movie> {
        return remoteLoader.loadMovie()
            .map { mapMovieDtoToMovie(it) }
            //.flatMap { Single.fromObservable(localDetailsSaver.save(it)) }
            .flatMap { Single.just(it) }
    }

    override fun loadSeatMap(): Single<SeatMap> {
        return remoteLoader.loadSeatMap()
            .map { mapSeatMapDtoToSeatMap(it) }
            //.flatMap { Single.fromObservable(localDetailsSaver.save(it)) }
            .flatMap { Single.just(it) }
    }

    override fun loadSchedule(): Single<Schedule> {
        return remoteLoader.loadSchedule()
            .map { mapScheduleDtoToSchedule(it) }
            //.flatMap { Single.fromObservable(localDetailsSaver.save(it)) }
            .flatMap { Single.just(it) }
    }
    private fun mapSeatMapDtoToSeatMap(dto: SeatMapDto): SeatMap {
        return SeatMap(
            "-1",
            dto.seatmap,
            availableSeatDtoToAvailableSeats(dto.availableSeats)
        )
    }

    private fun availableSeatDtoToAvailableSeats(dto: AvailableSeatsDto): AvailableSeats {
        return AvailableSeats(
            dto.seats,
            dto.seatCount
        )
    }

    private fun mapMovieDtoToMovie(dto: MovieDto): Movie {
        return Movie(
            dto.movieId,
            dto.canonicalTitle,
            dto.genre,
            dto.poster,
            dto.posterLandscape,
            dto.advisoryRating,
            dto.runtimeMins,
            dto.releaseDate,
            dto.synopsis,
            dto.cast
        )
    }
}