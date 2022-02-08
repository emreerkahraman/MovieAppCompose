package com.emreerkahraman.movieappcompose.ui.moviedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emreerkahraman.movieappcompose.di.repository.MovieRepository
import com.emreerkahraman.movieappcompose.model.Movie
import com.emreerkahraman.movieappcompose.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.collect



@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {
    var movieDetailViewState = MutableLiveData<MovieDetailViewState>()

    fun getMovieDetail(movieId: String) {
        viewModelScope.launch {
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






