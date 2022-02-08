package com.emreerkahraman.movieappcompose.model


import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class SpokenLanguage(
    @field:Json(name = "english_name")
    val englishName: String?,
    @field:Json(name = "iso_639_1")
    val iso6391: String?,
    @field:Json(name = "name")
    val name: String?
) : Parcelable