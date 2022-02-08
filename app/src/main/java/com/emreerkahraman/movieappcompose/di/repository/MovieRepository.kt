package com.emreerkahraman.movieappcompose.di.repository

import com.emreerkahraman.movieappcompose.api.TmdbService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val service: TmdbService):BaseRepository() {

    suspend  fun  getNowPlaying()= responseWrapper {
        service.getNowPlaying()
    }

    suspend  fun  getPopular()= responseWrapper {
        service.getPopular()
    }

    suspend  fun  getUpcoming()= responseWrapper {
        service.getUpcoming()
    }

    suspend  fun  getMovieDetail(movieId:String)= responseWrapper {
        service.getMovieDetail(movieId)
    }





}