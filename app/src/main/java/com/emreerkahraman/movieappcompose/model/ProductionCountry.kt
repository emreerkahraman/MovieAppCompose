package com.emreerkahraman.movieappcompose.model


import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ProductionCountry(
    @field:Json(name = "iso_3166_1")
    val iso31661: String?,
    @field:Json(name = "name")
    val name: String?
) : Parcelable