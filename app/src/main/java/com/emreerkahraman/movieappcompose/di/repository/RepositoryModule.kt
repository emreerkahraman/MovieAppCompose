package com.emreerkahraman.movieappcompose.di.repository

import com.emreerkahraman.movieappcompose.api.TmdbService
import com.emreerkahraman.movieappcompose.data.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object RepositoryModule {
    @Singleton
    @Provides
    fun provideMovieRepository(tmdbService: TmdbService): MovieRepository {
        return MovieRepository(tmdbService)
    }
}
