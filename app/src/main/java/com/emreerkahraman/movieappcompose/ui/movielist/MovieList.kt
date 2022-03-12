package com.emreerkahraman.movieappcompose.ui.movielist

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emreerkahraman.movieappcompose.R
import com.emreerkahraman.movieappcompose.model.Movie
import com.emreerkahraman.movieappcompose.model.Status
import com.emreerkahraman.movieappcompose.ui.theme.poppinsFamily
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun MovieList(viewModel: MovieListViewModel, onClickMovie: (Movie) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

    ) {
        Spacer(modifier = Modifier.height(16.dp))
        NowPlayingMovieList(viewModel, onClickMovie = onClickMovie)
        Spacer(modifier = Modifier.height(16.dp))
        PopularMovieList(viewModel, onClickMovie = onClickMovie)
        Spacer(modifier = Modifier.height(16.dp))
        UpcomingMovieList(viewModel, onClickMovie = onClickMovie)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun NowPlayingMovieList(
    viewModel: MovieListViewModel,
    onClickMovie: (Movie) -> Unit,
) {
    val nowPlayingState: MovieListViewState by viewModel.nowPlayingViewState
    if (nowPlayingState.isInitialized().not()) {
        LaunchedEffect(true) {
            viewModel.getNowPlaying()
        }
    }

    when (nowPlayingState.status) {

        Status.SUCCESS -> {
            Column {
                Title(stringResource(R.string.now_playing))
                MovieListComposable(
                    movieList = nowPlayingState.movieList!!,
                    onClickMovie = onClickMovie
                )
            }
        }

        Status.LOADING -> {
            MovieListPlaceHolder()
        }

        Status.ERROR -> {
            Log.i("error", nowPlayingState.error.toString())
        }
    }
}

@Composable
fun PopularMovieList(
    viewModel: MovieListViewModel,
    onClickMovie: (Movie) -> Unit,
) {
    val popularViewState: MovieListViewState by viewModel.popularViewState
    if (popularViewState.isInitialized().not()) {
        LaunchedEffect(true) {
            viewModel.getPopular()
        }
    }
    when (popularViewState.status) {
        Status.SUCCESS -> {
            Column {
                Title(stringResource(R.string.popular))
                MovieListComposable(
                    movieList = popularViewState.movieList!!,
                    onClickMovie = onClickMovie
                )
            }
        }
        Status.LOADING -> {
            MovieListPlaceHolder()
        }
        Status.ERROR -> {
            Log.i("error", popularViewState.error.toString())
        }
    }
}

@Composable
fun UpcomingMovieList(
    viewModel: MovieListViewModel,
    onClickMovie: (Movie) -> Unit,
) {
    val upcomingMovieState: MovieListViewState by viewModel.upcomingMovieState
    if (upcomingMovieState.isInitialized().not()) {
        LaunchedEffect(true) {
            viewModel.getUpcoming()
        }
    }
    when (upcomingMovieState.status) {
        Status.SUCCESS -> {
            Column {
                Title(stringResource(R.string.upcoming))
                MovieListComposable(
                    movieList = upcomingMovieState.movieList!!,
                    onClickMovie = onClickMovie
                )
            }
        }
        Status.LOADING -> {
            MovieListPlaceHolder()
        }
        Status.ERROR -> {
            Log.i("error", upcomingMovieState.error.toString())
        }
    }
}

@Composable
fun MovieListComposable(movieList: List<Movie>, onClickMovie: (Movie) -> Unit) {
    LazyRow {

        item {
            Spacer(modifier = Modifier.width(16.dp))
        }

        items(movieList) { movie: Movie ->
            MovieItem(movie = movie, onClickMovie = {
                onClickMovie.invoke(it)
            })
        }
    }
}

@Composable
fun Title(title: String) {
    Text(
        title,
        fontFamily = poppinsFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.Normal,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}
