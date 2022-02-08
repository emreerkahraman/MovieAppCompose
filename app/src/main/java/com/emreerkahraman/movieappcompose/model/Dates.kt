package com.emreerkahraman.movieappcompose.model


import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Dates(
    @Json(name = "maximum")
    val maximum: String?,
    @Json(name = "minimum")
    val minimum: String?
) : Parcelable