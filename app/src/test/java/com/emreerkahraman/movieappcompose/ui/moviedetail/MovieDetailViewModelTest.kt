package com.emreerkahraman.movieappcompose.ui.moviedetail

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.emreerkahraman.movieappcompose.data.repository.MovieRepository
import com.emreerkahraman.movieappcompose.util.CoroutineTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {

    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var mockContext: Context

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {

        mockContext = mock<Context>()
        movieDetailViewModel = MovieDetailViewModel(movieRepository)
    }
}
