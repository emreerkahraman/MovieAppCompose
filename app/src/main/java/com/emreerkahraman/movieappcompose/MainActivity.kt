package com.emreerkahraman.movieappcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.emreerkahraman.movieappcompose.model.Movie
import com.emreerkahraman.movieappcompose.model.Status
import com.emreerkahraman.movieappcompose.ui.moviedetail.MovieDetailViewModel
import com.emreerkahraman.movieappcompose.ui.movielist.*
import com.emreerkahraman.movieappcompose.ui.theme.MovieAppComposeTheme
import com.emreerkahraman.movieappcompose.ui.theme.poppinsFamily
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MovieApp()
                }
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun Dashboard(viewModel: MovieListViewModel, onClickMovie: (Movie) -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp)
        .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Title(stringResource(R.string.now_playing))
        NowPlayingMovieList(viewModel, onClickMovie = onClickMovie)
        Spacer(modifier = Modifier.height(16.dp))
        Title(stringResource(R.string.popular))
        PopularMovieList(viewModel, onClickMovie = onClickMovie)
        Spacer(modifier = Modifier.height(16.dp))
        Title(stringResource(R.string.upcoming))
        UpcomingMovieList(viewModel, onClickMovie = onClickMovie)
        Spacer(modifier = Modifier.height(16.dp))

    }
}

@ExperimentalPagerApi
@Composable
fun NowPlayingMovieList(
    movieListViewModel: MovieListViewModel,
    onClickMovie: (Movie) -> Unit,
) {
    val nowPlayingViewState: NowPlayingViewState by movieListViewModel.nowPlayingViewState.observeAsState(
        NowPlayingViewState())
    when (nowPlayingViewState.status) {

        Status.SUCCESS -> {
            MovieListComposable(movieList = nowPlayingViewState.movieList!!,
                onClickMovie = onClickMovie)
        }

        Status.LOADING -> {
            Loading()
        }

        Status.ERROR -> {
            Log.i("error", nowPlayingViewState.error.toString())
        }
    }


}


@Composable
fun PopularMovieList(
    movieListViewModel: MovieListViewModel,
    onClickMovie: (Movie) -> Unit,
) {
    val popularViewState: PopularViewState by movieListViewModel.popularViewState.observeAsState(
        PopularViewState())

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
    movieListViewModel: MovieListViewModel,
    onClickMovie: (Movie) -> Unit,
) {
    val upcomingMovieState: UpcomingMovieState by movieListViewModel.upcomingMovieState.observeAsState(
        UpcomingMovieState())

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

        items(movieList) { movie: Movie ->
            Movie(movie = movie, onClickMovie = {
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
    )

}

@Composable
fun Movie(
    movie: Movie,
    modifier: Modifier = Modifier,
    onClickMovie: (Movie) -> Unit,
) {
    Column {
        Image(
            painter = rememberImagePainter("https://image.tmdb.org/t/p/w342/${movie.posterPath}"),
            contentDescription = null,
            modifier = modifier
                .padding(end = 16.dp)
                .width(150.dp)
                .aspectRatio(0.66f)
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    onClickMovie.invoke(movie)
                }
        )

    }
}



@ExperimentalPagerApi
@Composable
fun MovieApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            val viewModel = hiltViewModel<MovieListViewModel>()
            Dashboard(
                viewModel = viewModel,
                onClickMovie = { movie ->
                    navController.navigate("movieDetail/" + movie.id)
                }
            )
        }
        composable("movieDetail/{movieId}") { backStackEntry ->
            val viewModel = hiltViewModel<MovieDetailViewModel>()
            MovieDetail(
                viewModel = viewModel,
                movieId = backStackEntry.arguments?.getString("movieId")!!,
                onClickBack = { navController.navigateUp() }
            )
        }
    }
}



