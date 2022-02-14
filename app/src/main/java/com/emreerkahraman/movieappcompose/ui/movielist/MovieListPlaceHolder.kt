package com.emreerkahraman.movieappcompose.ui.movielist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.emreerkahraman.movieappcompose.ui.moviedetail.shimmerPlaceHolder

@Composable
fun TitlePlaceHolder() {

    Text(
        text = "",
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(0.5f)
            .shimmerPlaceHolder()
    )
}

@Composable
fun MovieItemPlaceHolder() {

    Box(
        modifier = Modifier
            .padding(end = 8.dp)
            .width(100.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(8.dp))
            .shimmerPlaceHolder()
    )
}

@Composable
fun MovieListPlaceHolder() {
    Column {
        Spacer(modifier = Modifier.height(16.dp))
        TitlePlaceHolder()
        LazyRow {
            item {
                Spacer(modifier = Modifier.width(16.dp))
            }

            items(30) {
                MovieItemPlaceHolder()
            }
        }
    }
}
