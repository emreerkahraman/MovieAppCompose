package com.emreerkahraman.movieappcompose.ui.moviedetail

import com.emreerkahraman.movieappcompose.model.Movie
import com.emreerkahraman.movieappcompose.model.Status

class MovieDetailViewState(
    val status: Status = Status.LOADING,
    val error: String? = null,
    val movie: Movie? = null
)