package com.emreerkahraman.movieappcompose.ui.movielist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.emreerkahraman.movieappcompose.model.Movie

@Composable
fun MovieItem(
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
