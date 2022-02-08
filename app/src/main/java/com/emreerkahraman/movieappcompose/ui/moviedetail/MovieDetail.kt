package com.emreerkahraman.movieappcompose.ui.moviedetail

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.emreerkahraman.movieappcompose.R
import com.emreerkahraman.movieappcompose.model.Movie
import com.emreerkahraman.movieappcompose.model.Status
import com.emreerkahraman.movieappcompose.ui.theme.poppinsFamily
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun MovieDetail(viewModel: MovieDetailViewModel, movieId: String, onClickBack: () -> Unit) {

    val movieDetailViewState: MovieDetailViewState by viewModel.movieDetailViewState.observeAsState(
        MovieDetailViewState()
    )

    LaunchedEffect(true) {
        viewModel.getMovieDetail(movieId = movieId)
    }

    when (movieDetailViewState.status) {
        Status.SUCCESS -> {
            MovieDetail(movieDetailViewState.movie, onClickBack)
        }
        Status.LOADING -> {
            MovieDetailPlaceHolder()
        }
        Status.ERROR -> {
            Log.i("error", movieDetailViewState.error.toString())
        }
    }
}

@Composable
private fun MovieDetail(
    movie: Movie?,
    onClickBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Toolbar(title = stringResource(R.string.movie_detail)) {
            onClickBack.invoke()
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

            PosterImage(movie?.posterPath)
            Column(
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                CategoryCard()
                RuntimeCard(movie?.runtime.toString())
                RatingCard(movie?.voteAverage.toString())
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = movie?.title?.uppercase().toString(),
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = MaterialTheme.colors.secondary)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.synopsis),
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = movie?.overview.toString(),
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
private fun PosterImage(posterPath: String?) {
    Image(
        painter = rememberImagePainter("https://image.tmdb.org/t/p/w342/$posterPath"),
        contentDescription = null,
        modifier = Modifier
            .width(200.dp)
            .height(300.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}

@Composable
private fun CategoryCard() {
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
            Icon(
                painter = rememberImagePainter(R.drawable.ic_category),
                contentDescription = null
            )
            Text(text = "Category")
            Text(text = "Category")
        }
    }
}

@Composable
private fun RuntimeCard(runtime: String?) {
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
            Icon(
                painter = rememberImagePainter(R.drawable.ic_duration),
                contentDescription = null
            )
            Text(text = stringResource(R.string.duration))
            Text(text = runtime.toString())
        }
    }
}

@Composable
private fun RatingCard(rating: String?) {
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
            Icon(
                painter = rememberImagePainter(R.drawable.ic_rating),
                contentDescription = null
            )
            Text(text = stringResource(R.string.rating))
            Text(text = rating.toString())
        }
    }
}

@Composable
fun Toolbar(title: String, onClickBack: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = {
            onClickBack.invoke()
        }) {
            Icon(Icons.Default.ArrowBack, contentDescription = null)
        }
        Text(
            text = title,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Normal, fontSize = 16.sp
        )
    }
}
