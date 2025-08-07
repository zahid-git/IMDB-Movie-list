package com.ifarmer.movielist.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.ifarmer.movielist.R
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities

@Composable
fun CustomMovieGridView(movies: LazyPagingItems<MovieEntities>?){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2-column grid
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies!!.itemCount) { position ->
            val movie = movies[position]
            MovieCardView(movie)
        }
    }
}

@Composable
fun MovieCardView(movie: MovieEntities?){
    Card(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            modifier = Modifier.size(200.dp),
            model = movie?.posterUrl.toString(),
            contentDescription = "Movie Image",
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.placeholder)
        )
        movie?.title?.let { Text( text = it, modifier = Modifier.padding(16.dp)) }
    }
}

@Composable
@Preview
fun CustomMovieGridPreview(){
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
    MovieCardView(dummyData)
}