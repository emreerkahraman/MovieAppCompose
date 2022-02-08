package com.emreerkahraman.movieappcompose.ui.movielist

import com.emreerkahraman.movieappcompose.model.Movie
import com.emreerkahraman.movieappcompose.model.Status

class UpcomingMovieState(
    val status: Status = Status.LOADING,
    val error: String? = null,
    val movieList: List<Movie>? = null
)