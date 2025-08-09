package com.ifarmer.movielist.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.ifarmer.movielist.R
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieWithWishlistEntities


@Composable
fun CustomMovieListView(
    movieWishList: LazyPagingItems<MovieWithWishlistEntities>?,
    onItemClick: (movieId: Int?) -> Unit,
    onFavoriteClick: (id: Int?) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        movieWishList?.itemCount?.let {
            items(it) { position ->
                val movieWishData = movieWishList[position]
                MovieListItem(movieWishData = movieWishData, onItemClick = onItemClick, onFavoriteClick = onFavoriteClick)
            }
        }
    }
}

@Composable
fun MovieListItem(
    movieWishData: MovieWithWishlistEntities?,
    onItemClick: (movieId: Int?) -> Unit,
    onFavoriteClick: (id: Int?) -> Unit
) {
    val movie = movieWishData?.movie
    val isWishItem = movieWishData?.isWishlistItem

    Card(
        modifier = Modifier
            .wrapContentWidth(),
        onClick = { onItemClick(movie?.id) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(150.dp)
                    .wrapContentWidth(),
                alignment = Alignment.TopStart,
                model = movie?.posterUrl,
                contentDescription = "Movie Image",
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.placeholder)
            )
            Box {
                Image(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 5.dp, end = 5.dp)
                        .clip(CircleShape)
                        .clickable {
                            onFavoriteClick(movie?.id)
                        },
                    imageVector = if (isWishItem == true) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    colorFilter = ColorFilter.tint(if (isWishItem == true) Color.Red else Color.Gray),
                    contentDescription = "Favorite",
                )
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    movie?.title?.let {
                        Text(
                            modifier = Modifier
                                .wrapContentWidth()
                                .padding(top = 10.dp),
                            maxLines = 2,
                            fontSize = 18.sp,
                            text = it
                        )
                    }
                    movie?.year?.let {
                        Text(
                            modifier = Modifier.padding(top = 5.dp),
                            fontSize = 12.sp,
                            text = it
                        )
                    }
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        movie?.genres?.forEach { genre ->
                            Card(
                                modifier = Modifier
                                    .wrapContentWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFFD3D3D3), // Light cyan background
                                    contentColor = Color.Black // Text/Icon color
                                )
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(start = 8.dp, end = 8.dp),
                                    text = genre,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun CustomMovieListPreview() {
    var dummyData = MovieEntities(
        id = 1,
        title = "Dummy Title",
        year = "2025",
        runtime = "90",
        genres = arrayListOf(
            "Action",
            "Adventure",
            "Adventure",
            "Adventure",
            "Adventure",
            "Adventure",
            "Adventure",
            "Science Fiction"
        ),
        director = "Zahid",
        actors = "Zahidul Islam",
        plot = "N/A",
        posterUrl = "https://m.media-amazon.com/images/M/MV5BMTg4MDk1ODExN15BMl5BanBnXkFtZTgwNzIyNjg3MDE@._V1_FMjpg_UX1000_.jpg"
    )
    MovieListItem(
        MovieWithWishlistEntities(movie = dummyData, isWishlistItem = true),
        { } , { }
    )
}