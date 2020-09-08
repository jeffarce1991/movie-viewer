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

    private fun mapScheduleDtoToSchedule(it: ScheduleDto): Schedule {
        return Schedule(
            "-1",
            mapDatesDtoToDates(it.dates),
            mapCinemasDtoToCinemas(it.cinemas),
            mapTimesDtoToTimes(it.times)
        )
    }

    private fun mapDatesDtoToDates(dtoList: List<DateDto>): List<Schedule.Date> {
        val list : MutableList<Schedule.Date> = mutableListOf()
        for (d in dtoList) {
            list.add(Schedule.Date(d.id, d.label, d.date))
        }
        return list
    }

    /* CINEMAS */
    private fun mapCinemasDtoToCinemas(dtoList: List<CinemasDto>)
            : List<Schedule.Cinemas> {
        val list : MutableList<Schedule.Cinemas> = mutableListOf()
        for (c in dtoList) {
            list.add(Schedule.Cinemas(c.parent, mapCCtoToCC(c.cinemas)))
        }
        return list
    }

    /* CC = Cinemas.Cinemas */
    private fun mapCCtoToCC(dtoList: List<CinemaDto>)
            : List<Schedule.Cinemas.Cinema> {
        val list : MutableList<Schedule.Cinemas.Cinema> = mutableListOf()
        for (c in dtoList) {
            list.add(Schedule.Cinemas.Cinema(c.id, c.cinemaId, c.label))
        }
        return list
    }

    /* TIMES */
    private fun mapTimesDtoToTimes(dtoList: List<TimesDto>)
            : List<Schedule.Times> {
        val list : MutableList<Schedule.Times> = mutableListOf()
        val ttList : MutableList<Schedule.Times.Time> = mutableListOf()
        for (t in dtoList) {
            for (tt in t.times) {
                ttList.add(Schedule.Times.Time(
                    tt.id,
                    tt.label,
                    tt.scheduleId,
                    tt.popcornPrice,
                    tt.popcornLabel,
                    tt.seatingType,
                    tt.price,
                    tt.variant
                ))
            }
            list.add(Schedule.Times(t.parent, ttList))
        }
        return list
    }

    /* TT = Times.Times */
    private fun mapTTtoToTT(dtoList: List<TimeDto>)
            : List<Schedule.Times.Time> {
        val list : MutableList<Schedule.Times.Time> = mutableListOf()
        for (t in dtoList) {
            list.add(Schedule.Times.Time(
                t.id,
                t.label,
                t.scheduleId,
                t.popcornPrice,
                t.popcornLabel,
                t.seatingType,
                t.price,
                t.variant
            ))
        }
        return list
    }

    /* SEATMAP */
    private fun mapSeatMapDtoToSeatMap(dto: SeatMapDto): SeatMap {
        return SeatMap(
            "-1",
            dto.seatmap,
            mapAvailableSeatDtoToAvailableSeats(dto.availableSeats)
        )
    }


    private fun mapAvailableSeatDtoToAvailableSeats(dto: AvailableSeatsDto): SeatMap.AvailableSeats {
        return SeatMap.AvailableSeats(
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
            dto.cast,
            dto.theater
        )
    }
}