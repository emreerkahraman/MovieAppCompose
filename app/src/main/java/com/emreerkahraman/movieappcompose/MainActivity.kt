package com.emreerkahraman.movieappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emreerkahraman.movieappcompose.ui.moviedetail.MovieDetail
import com.emreerkahraman.movieappcompose.ui.moviedetail.MovieDetailViewModel
import com.emreerkahraman.movieappcompose.ui.movielist.MovieList
import com.emreerkahraman.movieappcompose.ui.movielist.MovieListViewModel
import com.emreerkahraman.movieappcompose.ui.theme.MovieAppComposeTheme
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
fun MovieApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "movieList") {
        composable("movieList") {
            val viewModel = hiltViewModel<MovieListViewModel>()
            MovieList(
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
