package com.emreerkahraman.movieappcompose.model


import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Popular(
    @field:Json(name = "page")
    val page: Int?,
    @field:Json(name = "results")
    val movieList: List<Movie>?,
    @field:Json(name = "total_pages")
    val totalPages: Int?,
    @field:Json(name = "total_results")
    val totalResults: Int?
) : Parcelable