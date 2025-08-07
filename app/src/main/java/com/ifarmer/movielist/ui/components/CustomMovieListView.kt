package com.ifarmer.movielist.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.ifarmer.movielist.R
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.data.model.response.MovieDataModel


@Composable
fun CustomMovieListView(movies: LazyPagingItems<MovieEntities>?) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        movies?.itemCount?.let {
            items(it) { position ->
                val movie = movies[position]
                MovieListItem(movie)
            }
        }
    }
}

@Composable
fun MovieListItem(movie: MovieEntities?) {
    Card(
        modifier = Modifier
            .wrapContentWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(180.dp)
                    .wrapContentWidth(),
                alignment = Alignment.TopStart,
                model = movie?.posterUrl,
                contentDescription = "Movie Image",
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.placeholder)
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
                            .padding(top = 5.dp),
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
                                containerColor = Color(0xFF989898), // Light cyan background
                                contentColor = Color.Black // Text/Icon color
                            )
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 10.dp, top = 3.dp, bottom = 3.dp),
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
        dummyData
    )
}