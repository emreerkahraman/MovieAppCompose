package com.emreerkahraman.movieappcompose.ui.moviedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.emreerkahraman.movieappcompose.data.repository.MovieRepository
import com.emreerkahraman.movieappcompose.model.Movie
import com.emreerkahraman.movieappcompose.model.Resource
import com.emreerkahraman.movieappcompose.model.Status
import com.emreerkahraman.movieappcompose.util.MainCoroutineRule
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.internal.matchers.apachecommons.ReflectionEquals
import java.io.IOException

@ExperimentalCoroutinesApi
class MovieDetailViewModelTest {

    @get:Rule
    val coroutineTestRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val movieRepository = mockk<MovieRepository>()
    private lateinit var viewModel: MovieDetailViewModel

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel(movieRepository = movieRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun movie_state_loading() = runTest {

        coEvery {
            movieRepository.getMovieDetail("1")
        } returns flow {
            emit(Resource(Status.LOADING, null, null))
        }
        viewModel.getMovieDetail("1")

        val loadingState = MovieDetailViewState(Status.LOADING, null, null)
        viewModel.movieDetailViewState.test(timeoutMs = 2000L) {
            assertTrue(ReflectionEquals(loadingState).matches(awaitItem()))
            cancelAndIgnoreRemainingEvents()
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun movie_state_success() = runTest {
        val file = this.javaClass.getResourceAsStream("/moviedetail.json")?.bufferedReader()
            .use { it?.readText() }
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<Movie> = moshi.adapter(Movie::class.java)
        val movie = jsonAdapter.fromJson(file!!)

        coEvery {
            movieRepository.getMovieDetail("1")
        } returns flow {
            emit(Resource(Status.SUCCESS, movie!!, null))
        }

        viewModel.getMovieDetail("1")

        val loadingState = MovieDetailViewState(Status.LOADING, null, null)
        val successState = MovieDetailViewState(Status.SUCCESS, null, movie = movie)
        viewModel.movieDetailViewState.test(timeoutMs = 2000L) {

            assertTrue(ReflectionEquals(loadingState).matches(awaitItem()))
            assertTrue(ReflectionEquals(successState).matches(awaitItem()))
            cancelAndIgnoreRemainingEvents()
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun movie_state_error() = runTest {

        coEvery {
            movieRepository.getMovieDetail("1")
        } returns flow {
            emit(Resource(Status.ERROR, null, IOException()))
        }

        viewModel.getMovieDetail("1")

        val loadingState = MovieDetailViewState(Status.LOADING, null, null)
        val errorState = MovieDetailViewState(Status.ERROR, IOException().message, null)
        viewModel.movieDetailViewState.test(timeoutMs = 2000L) {

            assertTrue(ReflectionEquals(loadingState).matches(awaitItem()))
            assertTrue(ReflectionEquals(errorState).matches(awaitItem()))
            cancelAndIgnoreRemainingEvents()
        }
    }
}
