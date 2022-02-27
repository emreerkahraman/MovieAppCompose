package com.emreerkahraman.movieappcompose.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(

    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "overview")
    val overview: String?,
    @field:Json(name = "runtime")
    val runtime: Int?,
    @field:Json(name = "poster_path")
    val posterPath: String?,
    @field:Json(name = "title")
    val title: String?,
    @field:Json(name = "vote_average")
    val voteAverage: Double?,
    @field:Json(name = "vote_count")
    val voteCount: Int?
) : Parcelable
