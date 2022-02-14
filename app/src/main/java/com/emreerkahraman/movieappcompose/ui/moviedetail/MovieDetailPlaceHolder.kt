package com.emreerkahraman.movieappcompose.ui.moviedetail

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@ExperimentalPagerApi
@Composable
fun MovieDetailPlaceHolder() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        ToolbarPlaceHolder()
        Spacer(modifier = Modifier.height(16.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            PosterPlaceHolder()
            MovieInfoCardsPlaceHolder()
        }

        Spacer(modifier = Modifier.height(16.dp))

        TitlePlaceHolder()

        Spacer(modifier = Modifier.height(16.dp))

        DividerPlaceHolder()

        Spacer(modifier = Modifier.height(16.dp))

        SynopsisTitlePlaceHolder()
        Spacer(modifier = Modifier.height(16.dp))
        SynopsisPlaceHolder()
    }
}

@Composable
private fun SynopsisPlaceHolder() {
    Text(
        text = "",
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .shimmerPlaceHolder(),
    )
}

@Composable
private fun SynopsisTitlePlaceHolder() {
    Text(
        text = "",
        modifier = Modifier
            .fillMaxWidth()
            .shimmerPlaceHolder(),
    )
}

@Composable
private fun DividerPlaceHolder() {
    Divider(
        color = MaterialTheme.colors.secondary,
        modifier = Modifier
            .height(1.dp)
            .shimmerPlaceHolder()
    )
}

@Composable
private fun TitlePlaceHolder() {
    Text(
        text = "",
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp)
            .shimmerPlaceHolder()
    )
}

@Composable
private fun ToolbarPlaceHolder() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = {
        }) {
            Icon(
                Icons.Default.ArrowBack, contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .shimmerPlaceHolder()
            )
        }
        Text(
            text = "",
            modifier = Modifier
                .fillMaxWidth()
                .shimmerPlaceHolder()
        )
    }
}

@Composable
private fun PosterPlaceHolder() {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(300.dp)
            .clip(RoundedCornerShape(8.dp))
            .shimmerPlaceHolder()
    ) {}
}

@Composable
private fun MovieInfoCardsPlaceHolder() {
    Column(
        modifier = Modifier.height(300.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        repeat(3) {
            Card(
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .shimmerPlaceHolder(),
                backgroundColor = MaterialTheme.colors.primary,
                border = BorderStroke(1.dp, MaterialTheme.colors.secondary)
            ) {}
        }
    }
}


fun Modifier.shimmerPlaceHolder() = composed {
    this.placeholder(
        visible = true,
        highlight = PlaceholderHighlight.shimmer(),
        color = MaterialTheme.colors.secondary
    )
}

