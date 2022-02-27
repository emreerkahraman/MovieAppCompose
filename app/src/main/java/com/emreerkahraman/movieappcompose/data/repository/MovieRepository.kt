package com.emreerkahraman.movieappcompose.data.repository

import com.emreerkahraman.movieappcompose.model.Movie
import com.emreerkahraman.movieappcompose.model.NowPlaying
import com.emreerkahraman.movieappcompose.model.Popular
import com.emreerkahraman.movieappcompose.model.Resource
import com.emreerkahraman.movieappcompose.model.Upcoming
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getNowPlaying(): Flow<Resource<NowPlaying?>>

    suspend fun getPopular(): Flow<Resource<Popular?>>

    suspend fun getUpcoming(): Flow<Resource<Upcoming?>>

    suspend fun getMovieDetail(movieId: String): Flow<Resource<Movie?>>
}
