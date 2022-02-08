package com.emreerkahraman.movieappcompose.ui.movielist

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.emreerkahraman.movieappcompose.R
import com.emreerkahraman.movieappcompose.model.Status
import com.emreerkahraman.movieappcompose.ui.moviedetail.MovieDetailViewModel
import com.emreerkahraman.movieappcompose.ui.moviedetail.MovieDetailViewState
import com.emreerkahraman.movieappcompose.ui.theme.poppinsFamily
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer


@ExperimentalPagerApi
@Composable
fun MovieDetail(viewModel: MovieDetailViewModel, movieId: String, onClickBack: () -> Unit) {

    val movieDetailViewState: MovieDetailViewState by viewModel.movieDetailViewState.observeAsState(
        MovieDetailViewState())


    LaunchedEffect(true) {
        viewModel.getMovieDetail(movieId = movieId)
    }

    when (movieDetailViewState.status) {
        Status.SUCCESS -> {

            val movie = movieDetailViewState.movie
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .verticalScroll(rememberScrollState())) {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            onClickBack.invoke()
                        }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = null)

                        }
                    },
                    title = {
                        Text(text = stringResource(id = R.string.movie_detail))
                    },
                    elevation = 0.dp
                )
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {


                    Image(
                        painter = rememberImagePainter("https://image.tmdb.org/t/p/w342/${movie?.posterPath}"),
                        contentDescription = null,
                        modifier = Modifier
                            .width(200.dp)
                            .height(300.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )

                    Column(modifier = Modifier.height(300.dp),
                        verticalArrangement = Arrangement.SpaceBetween) {
                        Card(
                            modifier = Modifier
                                .width(80.dp)
                                .height(80.dp),
                            backgroundColor = MaterialTheme.colors.primary,
                            border = BorderStroke(1.dp, MaterialTheme.colors.secondary)
                        ) {

                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(painter = rememberImagePainter(R.drawable.ic_category),
                                    contentDescription = null)
                                Text(text = "Category")
                                Text(text = "Category")
                            }

                        }
                        Card(
                            modifier = Modifier
                                .width(80.dp)
                                .height(80.dp),
                            backgroundColor = MaterialTheme.colors.primary,
                            border = BorderStroke(1.dp, MaterialTheme.colors.secondary)
                        ) {
                            Column(modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(painter = rememberImagePainter(R.drawable.ic_duration),
                                    contentDescription = null)
                                Text(text = "Category")
                                Text(text = stringResource(R.string.category))
                            }
                        }
                        Card(
                            modifier = Modifier
                                .width(80.dp)
                                .height(80.dp),
                            backgroundColor = MaterialTheme.colors.primary,
                            border = BorderStroke(1.dp, MaterialTheme.colors.secondary)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(painter = rememberImagePainter(R.drawable.ic_rating),
                                    contentDescription = null)
                                Text(text = stringResource(R.string.rating))
                                Text(text = movie?.voteAverage.toString())
                            }

                        }
                    }

                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = movie?.title?.toUpperCase().toString(),
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(16.dp))

                Divider(color = MaterialTheme.colors.secondary)

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = stringResource(R.string.synopsis),
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = movie?.overview.toString(),
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Justify)


            }
        }
        Status.LOADING -> {
            MovieDetailLoading()
        }
        Status.ERROR -> {
            Log.i("error", movieDetailViewState.error.toString())
        }
    }


}


@ExperimentalPagerApi
@Composable
fun MovieDetailLoading() {

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
        .verticalScroll(rememberScrollState())) {

        TopAppBar(
            navigationIcon = {
                IconButton(onClick = {

                }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)

                }
            },
            title = {
                Text(text = stringResource(R.string.movie_detail),
                    modifier = Modifier.placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer())
                )
            },
            elevation = 0.dp
        )

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

            Card(
                modifier = Modifier
                    .width(200.dp)
                    .height(300.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(),
                    )
            ) {}

            Column(modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.SpaceBetween) {

                repeat(3){
                    Card(
                        modifier = Modifier
                            .width(80.dp)
                            .height(80.dp)
                            .placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.shimmer(),
                            ),
                        backgroundColor = MaterialTheme.colors.primary,
                        border = BorderStroke(1.dp, MaterialTheme.colors.secondary)
                    ) {}
                }

            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .placeholder(
                    visible = true,
                    highlight = PlaceholderHighlight.shimmer()
                ),
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        Divider(color = MaterialTheme.colors.secondary,modifier = Modifier
            .height(1.dp)
            .placeholder(
                visible = true,
                highlight = PlaceholderHighlight.shimmer())
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "",
            modifier = Modifier
                .fillMaxWidth()
                .placeholder(
                    visible = true,
                    highlight = PlaceholderHighlight.shimmer())
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .placeholder(
                    visible = true,
                    highlight = PlaceholderHighlight.shimmer()))


    }
}

