package com.ifarmer.movielist.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
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
fun CustomMovieGridView(
    movieWishList: LazyPagingItems<MovieWithWishlistEntities>?,
    onItemClick: (id: Int?) -> Unit,
    onFavoriteClick: (id: Int?) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.wrapContentSize(),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movieWishList!!.itemCount) { position ->
            val movieWishData = movieWishList[position]
            MovieCardView(movieWishData = movieWishData, onItemClick, onFavoriteClick)
        }
    }
}

@Composable
fun MovieCardView(movieWishData: MovieWithWishlistEntities?, onItemClick: (id: Int?) -> Unit, onFavoriteClick: (id: Int?) -> Unit) {
    val movie = movieWishData?.movie
    val isWishItem = movieWishData?.isWishlistItem

    Card(modifier = Modifier.fillMaxWidth(), onClick = { onItemClick(movie?.id) }) {
        Box(

        ) {
            Column {
                AsyncImage(
                    modifier = Modifier.size(240.dp),
                    alignment = Alignment.Center,
                    model = movie?.posterUrl.toString(),
                    contentDescription = "Movie Image",
                    placeholder = painterResource(R.drawable.placeholder),
                    error = painterResource(R.drawable.placeholder),
                    contentScale = ContentScale.FillWidth
                )
                movie?.title?.let {
                    Text(
                        text = it,
                        maxLines = 1,
                        modifier = Modifier.padding(15.dp),
                        fontSize = 18.sp
                    )
                }
            }
            Image(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 5.dp, end = 5.dp)
                    .clip(CircleShape)
                    .clickable {
                        onFavoriteClick(movie?.id)
                    },
                imageVector = if(isWishItem == true) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                colorFilter = ColorFilter.tint(if(isWishItem == true) Color.Red else Color.Gray),
                contentDescription = "Favorite",
            )
        }
    }
}

@Composable
@Preview
fun CustomMovieGridPreview() {
    var dummyData = MovieEntities(
        id = 1,
        title = "Dummy Title",
        year = "2025",
        runtime = "90",
        genres = arrayListOf(),
        director = "Zahid",
        actors = "Zahidul Islam",
        plot = "N/A",
        posterUrl = "https://m.media-amazon.com/images/M/MV5BMTg4MDk1ODExN15BMl5BanBnXkFtZTgwNzIyNjg3MDE@._V1_FMjpg_UX1000_.jpg"
    )
    MovieCardView(MovieWithWishlistEntities(movie = dummyData, isWishlistItem = true), { }, { })
}