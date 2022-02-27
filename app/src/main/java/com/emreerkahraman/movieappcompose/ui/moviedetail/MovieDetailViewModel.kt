package com.emreerkahraman.movieappcompose.ui.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emreerkahraman.movieappcompose.data.repository.MovieRepository
import com.emreerkahraman.movieappcompose.model.Movie
import com.emreerkahraman.movieappcompose.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    var movieDetailViewState = MutableStateFlow(MovieDetailViewState())

    fun getMovieDetail(movieId: String) {
        viewModelScope.launch() {
            movieRepository.getMovieDetail(movieId = movieId).collect {
                delay(1000L)
                onGetMovieDetail(it)
            }
        }
    }

    private fun onGetMovieDetail(resource: Resource<Movie?>) {
        movieDetailViewState.value = MovieDetailViewState(
            status = resource.status,
            error = resource.error?.message,
            movie = resource.data
        )
    }
}
