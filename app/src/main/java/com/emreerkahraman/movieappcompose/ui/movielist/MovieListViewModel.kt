package com.emreerkahraman.movieappcompose.ui.movielist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emreerkahraman.movieappcompose.data.repository.MovieRepository
import com.emreerkahraman.movieappcompose.model.NowPlaying
import com.emreerkahraman.movieappcompose.model.Popular
import com.emreerkahraman.movieappcompose.model.Resource
import com.emreerkahraman.movieappcompose.model.Upcoming
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    var nowPlayingViewState = mutableStateOf(MovieListViewState())
    var popularViewState = mutableStateOf(MovieListViewState())
    var upcomingMovieState = mutableStateOf(MovieListViewState())

    init {
        getNowPlaying()
        getPopular()
        getUpcoming()
    }

    private fun getNowPlaying() {
        viewModelScope.launch {
            movieRepository.getNowPlaying().collect {
                delay(500L)
                onGetNowPlaying(it)
            }
        }
    }

    private fun getPopular() {
        viewModelScope.launch {
            movieRepository.getPopular().collect {
                delay(500L)
                onGetPopular(it)
            }
        }
    }

    private fun getUpcoming() {
        viewModelScope.launch {
            movieRepository.getUpcoming().collect {
                delay(500L)
                onGetUpcoming(it)
            }
        }
    }

    private fun onGetNowPlaying(resource: Resource<NowPlaying?>) {
        nowPlayingViewState.value = MovieListViewState(
            status = resource.status,
            error = resource.error?.message,
            movieList = resource.data?.movieList
        )
    }

    private fun onGetPopular(resource: Resource<Popular?>) {
        popularViewState.value = MovieListViewState(
            status = resource.status,
            error = resource.error?.message,
            movieList = resource.data?.movieList
        )
    }

    private fun onGetUpcoming(resource: Resource<Upcoming?>) {
        upcomingMovieState.value = MovieListViewState(
            status = resource.status,
            error = resource.error?.message,
            movieList = resource.data?.movieList
        )
    }
}
