package com.emreerkahraman.movieappcompose.ui.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emreerkahraman.movieappcompose.di.repository.MovieRepository
import com.emreerkahraman.movieappcompose.model.NowPlaying
import com.emreerkahraman.movieappcompose.model.Popular
import com.emreerkahraman.movieappcompose.model.Resource
import com.emreerkahraman.movieappcompose.model.Upcoming
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    var nowPlayingViewState = MutableLiveData<NowPlayingViewState>()
    var popularViewState = MutableLiveData<PopularViewState>()
    var upcomingMovieState = MutableLiveData<UpcomingMovieState>()


    init {
        getNowPlaying()
        getPopular()
        getUpcoming()
    }

    fun getNowPlaying() {
        viewModelScope.launch {
            movieRepository.getNowPlaying().collect {
                onGetNowPlaying(it)
            }
        }
    }
    fun getPopular() {
        viewModelScope.launch {
            movieRepository.getPopular().collect {
                onGetPopular(it)
            }
        }
    }
    fun getUpcoming() {
        viewModelScope.launch {
            movieRepository.getUpcoming().collect {
                onGetUpcoming(it)
            }
        }
    }

    private fun onGetNowPlaying(resource: Resource<NowPlaying?>) {
        nowPlayingViewState.value = NowPlayingViewState(
            status = resource.status,
            error = resource.error?.message,
            movieList = resource.data?.movieList
        )

    }
    private fun onGetPopular(resource: Resource<Popular?>) {
        popularViewState.value = PopularViewState(
            status = resource.status,
            error = resource.error?.message,
            movieList = resource.data?.movieList
        )

    }

    private fun onGetUpcoming(resource: Resource<Upcoming?>) {
        upcomingMovieState.value = UpcomingMovieState(
            status = resource.status,
            error = resource.error?.message,
            movieList = resource.data?.movieList
        )

    }


}