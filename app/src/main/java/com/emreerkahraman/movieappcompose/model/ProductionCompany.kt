package com.emreerkahraman.movieappcompose.model


import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ProductionCompany(
   @field:Json(name = "id")
    val id: Int?,
   @field:Json(name = "logo_path")
    val logoPath: String?,
   @field:Json(name = "name")
    val name: String?,
   @field:Json(name = "origin_country")
    val originCountry: String?
) : Parcelable