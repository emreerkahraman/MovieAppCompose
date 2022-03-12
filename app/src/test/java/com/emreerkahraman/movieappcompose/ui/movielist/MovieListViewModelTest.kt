package com.emreerkahraman.movieappcompose.ui.movielist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.emreerkahraman.movieappcompose.data.repository.MovieRepository
import com.emreerkahraman.movieappcompose.model.Popular
import com.emreerkahraman.movieappcompose.model.Resource
import com.emreerkahraman.movieappcompose.model.Status
import com.emreerkahraman.movieappcompose.util.MainCoroutineRule
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.internal.matchers.apachecommons.ReflectionEquals

@ExperimentalCoroutinesApi
class MovieListViewModelTest {

    @get:Rule
    val mainCourotineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val movieRepository = mockk<MovieRepository>()

    private lateinit var viewModel: MovieListViewModel

    @Before
    fun setUp() {
        viewModel =
            MovieListViewModel(mainCourotineRule.dispatcher, movieRepository = movieRepository)
    }

    @Test
    fun popular_movie_success_state() {
        val file = this.javaClass.getResourceAsStream("/movielist.json")?.bufferedReader()
            .use { it?.readText() }
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<Popular> = moshi.adapter(Popular::class.java)
        val popularMovies = jsonAdapter.fromJson(file!!)

        coEvery {
            movieRepository.getPopular()
        } returns flow {
            emit(Resource(Status.SUCCESS, popularMovies!!, null))
        }

        viewModel.getPopular()

        mainCourotineRule.scheduler.advanceUntilIdle()
        val successState =
            MovieListViewState(Status.SUCCESS, null, movieList = popularMovies?.movieList)

        assertTrue(ReflectionEquals(successState).matches(viewModel.popularViewState.value))
    }
}
