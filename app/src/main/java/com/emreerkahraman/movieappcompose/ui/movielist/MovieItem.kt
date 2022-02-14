package com.emreerkahraman.movieappcompose.ui.movielist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
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
                .padding(end = 8.dp)
                .width(100.dp)
                .height(150.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    onClickMovie.invoke(movie)
                }
        )
    }
}
