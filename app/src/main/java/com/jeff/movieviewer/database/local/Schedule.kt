package com.jeff.movieviewer.database.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Schedule.TABLE_NAME)
data class Schedule(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: String?,
    @ColumnInfo(name = "dates")
    var dates: List<Date>,
    @ColumnInfo(name = "cinemas")
    var cinemas: List<Cinemas>,
    @ColumnInfo(name = "times")
    var times: List<Times>) {

    constructor() : this("-1", listOf<Date>(), listOf<Cinemas>(), listOf<Times>())

    companion object {
        const val COLUMN_ID = "id"
        const val TABLE_NAME = "schedules"
    }

    data class Date (
        var id: String,
        var label: String,
        var date: String
    ) {
        constructor() : this("", "", "")
    }

    data class Cinemas (
        var parent: String,
        val cinemas: List<Cinema>
    ) {
        constructor() : this("", listOf<Cinema>())

        data class Cinema (
            var id: String,
            var cinemaId: String,
            val label: String
        ) {
            constructor() : this("", "", "")
        }

    }

    data class Times (
        var parent: String,
        val times: List<Time>
    ) {
        constructor() : this("", listOf())

        data class Time (
            var id: String,
            var label: String,
            val scheduleId: String,
            val popcornPrice: String,
            val popcornLabel: String,
            val seatingType: String,
            val price: String,
            val variant: String? = null
        ) {
            constructor() : this("", "", "", "", "", "", "", null)
        }

    }
}
