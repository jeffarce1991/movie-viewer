package com.jeff.movieviewer.database.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Movie.TABLE_NAME)
data class Movie constructor(
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: String,
    @ColumnInfo(name = "canonical_title")
    var canonicalTitle: String,
    @ColumnInfo(name = "genre")
    var genre: String,
    @ColumnInfo(name = "poster")
    var poster: String,
    @ColumnInfo(name = "poster_landscape")
    var posterLandscape: String,
    @ColumnInfo(name = "advisory_rating")
    var advisoryRating: String,
    @ColumnInfo(name = "runtime_mins")
    var runtimeMins: String,
    @ColumnInfo(name = "release_date")
    var releaseDate: String,
    @ColumnInfo(name = "synopsis")
    var synopsis: String,
    @ColumnInfo(name = "cast")
    var cast: List<String>,
    @ColumnInfo(name = "theatre")
    var theatre: String) {

    constructor() : this("-1", "", "", "", "", "", "", "", "", emptyList(), "")

    companion object {
        const val COLUMN_ID = "id"
        const val TABLE_NAME = "movies"
    }
}
