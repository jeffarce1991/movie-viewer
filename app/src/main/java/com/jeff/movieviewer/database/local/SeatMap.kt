package com.jeff.movieviewer.database.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = SeatMap.TABLE_NAME)
data class SeatMap constructor(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: String?,
    @ColumnInfo(name = "seatmap")
    var seatmap: List<List<String>>,
    @ColumnInfo(name = "genre")
    var availableSeats: AvailableSeats) {

    data class AvailableSeats (
        var available: List<String>,
        var seatCount: Int
    ) {
        constructor() : this(listOf(), -1)
    }

    constructor() : this("", listOf<List<String>>(listOf()), AvailableSeats())

    companion object {
        const val COLUMN_ID = "id"
        const val TABLE_NAME = "seatmaps"
    }
}
