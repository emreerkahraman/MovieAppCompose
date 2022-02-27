package com.emreerkahraman.movieappcompose.data.repository

import com.emreerkahraman.movieappcompose.api.TmdbService
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val service: TmdbService) :
    BaseRepository(),
    MovieRepository {

    override suspend fun getNowPlaying() = responseWrapper {
        service.getNowPlaying()
    }

    override suspend fun getPopular() = responseWrapper {
        service.getPopular()
    }

    override suspend fun getUpcoming() = responseWrapper {
        service.getUpcoming()
    }

    override suspend fun getMovieDetail(movieId: String) = responseWrapper {
        service.getMovieDetail(movieId)
    }
}
