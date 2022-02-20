package com.emreerkahraman.movieappcompose.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class BelongsToCollection(
    @field:Json(name = "backdrop_path")
    val backdropPath: String?,
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "poster_path")
    val posterPath: String?
) : Parcelable
