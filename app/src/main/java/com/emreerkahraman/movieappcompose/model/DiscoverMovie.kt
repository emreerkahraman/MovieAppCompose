package com.emreerkahraman.movieappcompose.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class DiscoverMovie(
    @field:Json(name = "page")
    val page: Int?,
    @field:Json(name = "results")
    val movieList: List<Movie>?,
    @field:Json(name = "total_pages")
    val totalPages: Int?,
    @field:Json(name = "total_results")
    val totalResults: Int?
) : Parcelable
