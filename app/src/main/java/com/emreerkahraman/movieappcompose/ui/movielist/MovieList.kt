package com.emreerkahraman.movieappcompose.ui.movielist

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emreerkahraman.movieappcompose.model.Status
import com.emreerkahraman.movieappcompose.ui.theme.poppinsFamily
import com.google.accompanist.pager.ExperimentalPagerApi
import com.emreerkahraman.movieappcompose.R
import com.emreerkahraman.movieappcompose.model.Movie

@ExperimentalPagerApi
@Composable
fun MovieList(viewModel: MovieListViewModel, onClickMovie: (Movie) -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Title(stringResource(R.string.now_playing))
        NowPlayingMovieList(viewModel.nowPlayingViewState, onClickMovie = onClickMovie)
        Spacer(modifier = Modifier.height(16.dp))
        Title(stringResource(R.string.popular))
        PopularMovieList(viewModel.popularViewState, onClickMovie = onClickMovie)
        Spacer(modifier = Modifier.height(16.dp))
        Title(stringResource(R.string.upcoming))
        UpcomingMovieList(viewModel.upcomingMovieState, onClickMovie = onClickMovie)
        Spacer(modifier = Modifier.height(16.dp))

    }
}

@ExperimentalPagerApi
@Composable
fun NowPlayingMovieList(
    nowPlayingViewState: State<MovieListViewState>,
    onClickMovie: (Movie) -> Unit,
) {
    val nowPlayingState: MovieListViewState by nowPlayingViewState
    when (nowPlayingState.status) {

        Status.SUCCESS -> {
            MovieListComposable(movieList = nowPlayingState.movieList!!,
                onClickMovie = onClickMovie)
        }

        Status.LOADING -> {
            Loading()
        }

        Status.ERROR -> {
            Log.i("error", nowPlayingState.error.toString())
        }
    }


}


@Composable
fun PopularMovieList(
    popularMovieViewState: State<MovieListViewState>,
    onClickMovie: (Movie) -> Unit,
) {
    val popularViewState: MovieListViewState by popularMovieViewState
    when (popularViewState.status) {
        Status.SUCCESS -> {
            MovieListComposable(movieList = popularViewState.movieList!!,
                onClickMovie = onClickMovie)
        }
        Status.LOADING -> {
            Loading()
        }
        Status.ERROR -> {
            Log.i("error", popularViewState.error.toString())
        }
    }


}

@Composable
fun UpcomingMovieList(
    upComingMovieViewState: State<MovieListViewState>,
    onClickMovie: (Movie) -> Unit,
) {
    val upcomingMovieState: MovieListViewState by upComingMovieViewState

    when (upcomingMovieState.status) {
        Status.SUCCESS -> {
            MovieListComposable(movieList = upcomingMovieState.movieList!!,
                onClickMovie = onClickMovie)
        }
        Status.LOADING -> {
            Loading()
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
fun Loading() {
    Box(Modifier
        .fillMaxWidth()
        .height(200.dp), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = MaterialTheme.colors.primaryVariant)
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