package com.emreerkahraman.movieappcompose.data.repository

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.emreerkahraman.movieappcompose.api.TmdbService
import com.emreerkahraman.movieappcompose.model.Movie
import com.emreerkahraman.movieappcompose.model.NowPlaying
import com.emreerkahraman.movieappcompose.model.Popular
import com.emreerkahraman.movieappcompose.model.Status
import com.emreerkahraman.movieappcompose.model.Upcoming
import com.emreerkahraman.movieappcompose.util.CoroutineTestRule
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import java.io.IOException

class MovieRepositoryTest {

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var tmdbService: TmdbService

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieRepository: MovieRepository

    @Before
    fun setUp() {
        mockContext = mock()
        tmdbService = mock()
        movieRepository = MovieRepositoryImpl(tmdbService)
    }

    @Test
    fun get_movie_detail_return_success() = runBlocking {

        val file = this.javaClass.getResourceAsStream("/moviedetail.json")?.bufferedReader()
            .use { it?.readText() }
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<Movie> = moshi.adapter(Movie::class.java)
        val movie = jsonAdapter.fromJson(file!!)

        tmdbService.stub {
            onBlocking {
                getMovieDetail("1")
            }.doReturn(movie)
        }
        val result = movieRepository.getMovieDetail("1")

        result.collect {
            if (it.status == Status.SUCCESS) {
                assertEquals(movie, it.data)
            }
        }
    }

    @Test
    fun get_movie_details_should_return_exception() = runBlocking {

        tmdbService.stub {
            onBlocking {
                getMovieDetail("1")
            }.doAnswer { throw IOException() }
        }
        val result = movieRepository.getMovieDetail("1")

        result.collect {
            if (it.status == Status.ERROR) {
                assertNotNull(it.error)
            }
        }
    }

    @Test
    fun get_movie_detail_should_get_loading_first() {
        runBlocking {
            tmdbService.stub {
                onBlocking {
                    getMovieDetail("1")
                }.doAnswer { throw IOException() }
            }
            val result = movieRepository.getMovieDetail("1").first()
            assertEquals(result.status, Status.LOADING)
        }
    }

    @Test
    fun get_now_playing_return_success() = runBlocking {

        val file = this.javaClass.getResourceAsStream("/movielist.json")?.bufferedReader()
            .use { it?.readText() }
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<NowPlaying> = moshi.adapter(NowPlaying::class.java)
        val nowPlaying = jsonAdapter.fromJson(file!!)

        tmdbService.stub {
            onBlocking {
                getNowPlaying()
            }.doReturn(nowPlaying)
        }
        val result = movieRepository.getNowPlaying()

        result.collect {
            if (it.status == Status.SUCCESS) {
                assertEquals(nowPlaying, it.data)
                assertNotNull(it.data?.movieList)
            }
        }
    }

    @Test
    fun get_up_coming_return_success() = runBlocking {

        val file = this.javaClass.getResourceAsStream("/movielist.json")?.bufferedReader()
            .use { it?.readText() }
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<Upcoming> = moshi.adapter(Upcoming::class.java)
        val upComing = jsonAdapter.fromJson(file!!)

        tmdbService.stub {
            onBlocking {
                getUpcoming()
            }.doReturn(upComing)
        }
        val result = movieRepository.getNowPlaying()

        result.collect {
            if (it.status == Status.SUCCESS) {
                assertEquals(upComing, it.data)
                assertNotNull(it.data?.movieList)
            }
        }
    }

    @Test
    fun get_popular_return_success() = runBlocking {

        val file = this.javaClass.getResourceAsStream("/movielist.json")?.bufferedReader()
            .use { it?.readText() }
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<Popular> = moshi.adapter(Popular::class.java)
        val popular = jsonAdapter.fromJson(file!!)

        tmdbService.stub {
            onBlocking {
                getPopular()
            }.doReturn(popular)
        }
        val result = movieRepository.getNowPlaying()

        result.collect {
            if (it.status == Status.SUCCESS) {
                assertEquals(popular, it.data)
                assertNotNull(it.data?.movieList)
            }
        }
    }
}
