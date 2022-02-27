package com.emreerkahraman.movieappcompose.api

import com.emreerkahraman.movieappcompose.model.Movie
import com.emreerkahraman.movieappcompose.model.NowPlaying
import com.emreerkahraman.movieappcompose.model.Popular
import com.emreerkahraman.movieappcompose.model.Upcoming
import retrofit2.http.GET
import retrofit2.http.Path

interface TmdbService {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(): NowPlaying?

    @GET("movie/popular")
    suspend fun getPopular(): Popular?

    @GET("movie/upcoming")
    suspend fun getUpcoming(): Upcoming?

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: String): Movie?
}
