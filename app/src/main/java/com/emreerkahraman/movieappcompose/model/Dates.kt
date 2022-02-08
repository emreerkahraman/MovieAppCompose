package com.emreerkahraman.movieappcompose.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dates(
    @Json(name = "maximum")
    val maximum: String?,
    @Json(name = "minimum")
    val minimum: String?
) : Parcelable
