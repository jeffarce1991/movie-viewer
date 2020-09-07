package com.jeff.movieviewer.webservices.dto

import com.google.gson.annotations.SerializedName

class MovieDto(
    @field:SerializedName("movie_id") var movieId: String,
    @field:SerializedName("advisory_rating") var advisoryRating: String,
    @field:SerializedName("canonical_title") var canonicalTitle: String,
    @field:SerializedName("cast") var cast: List<String>,
    @field:SerializedName("genre") var genre: String,
    @field:SerializedName("has_schedules") var hasschedules: Int,
    @field:SerializedName("is_inactive") var is_inactive: Int,
    @field:SerializedName("is_showing") var isShowing: Int,
    @field:SerializedName("link_name") var linkName: String,
    @field:SerializedName("poster") var poster: String,
    @field:SerializedName("poster_landscape") var posterLandscape: String,
    @field:SerializedName("release_date") var releaseDate: String,
    @field:SerializedName("runtime_mins") var runtimeMins: String,
    @field:SerializedName("synopsis") var synopsis: String,
    @field:SerializedName("trailer") var trailer: String,
    @field:SerializedName("average_rating") var averageRating: String,
    @field:SerializedName("total_review") var totalReviews: String,
    @field:SerializedName("variants") var variants: List<String>,
    @field:SerializedName("theater") var theater: String,
    @field:SerializedName("order") var order: Int,
    @field:SerializedName("is_featured") var isFeatured: Int,
    @field:SerializedName("watch_list") var watchList: Boolean,
    @field:SerializedName("your_rating") var yourRating: Int
)

/*{
   "movie_id":"465e5cd3-9f14-4161-8900-93bcec6e71b8",
   "advisory_rating":"G",
   "canonical_title":"Test Movie",
   "cast":[
      "Scarlett Johansson1",
      "Michael Wincott",
      "Michael Pitt"
   ],
   "genre":"Action, Crime, Drama ",
   "has_schedules":1,
   "is_inactive":0,
   "is_showing":1,
   "link_name":"ghost-shell",
   "poster":"http:\/\/lh3.googleusercontent.com\/hkpMC-4LxMoR07ZAbpKgu_6haxlP8WTvp7t5I8eusOHLAqVeEaGA14Wom9hhikRaQUo0zy1xHQzhQf81Xq-G_BURkak",
   "poster_landscape":"http:\/\/lh3.googleusercontent.com\/7kEQdTRBBqkSM3MIDmnmf5xi02NpAe-fb1RIxiiaj4hbvQUwVXHGfnFhn_gNL4rPudhTKy84eva7EJwCeaAkoJklpg",
   "ratings":{

   },
   "release_date":"2017-05-29",
   "runtime_mins":"60.00",
   "synopsis":"A cyborg policewoman attempts to bring down a nefarious computer hacker.",
   "trailer":"tRkb1X9ovI4",
   "average_rating":null,
   "total_reviews":null,
   "variants":[
      "4DX",
      "3D\/4DX",
      "ATMOS",
      "3D"
   ],
   "theater": "Glorietta 3",
   "order":0,
   "is_featured":0,
   "watch_list":false,
   "your_rating":0
}*/