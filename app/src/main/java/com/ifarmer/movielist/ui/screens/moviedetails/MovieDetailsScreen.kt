package com.ifarmer.movielist.ui.screens.moviedetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ifarmer.movielist.R
import kotlinx.coroutines.flow.StateFlow


@Composable
fun MovieDetailsScreen(
    navController: NavHostController,
    viewState: StateFlow<MovieDetailsViewState>,
    movieId: Int
) {

}

@Composable
private fun ShowMovieDetailsScreen(
    viewState: MovieDetailsViewState
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier.size(height = 200.dp, width = 125.dp),
            model = viewState.image,
            contentDescription = "Movie Image"
        )
        Text(modifier = Modifier.padding(top = 20.dp), fontSize = 20.sp, text = viewState.movieName)
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            /*movie?.genres?.forEach { genre ->
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
            }*/
        }

        Row(

        ) {
            Row(
                modifier = Modifier
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.ic_calendar),
                    contentDescription = "Calender"
                )
                Text(modifier = Modifier.padding(start = 10.dp), text = viewState.releaseYear)
            }

            Row(
                modifier = Modifier
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.ic_time_clock),
                    contentDescription = "Movie Time"
                )
                Text(modifier = Modifier.padding(start = 10.dp), text = viewState.movieDuration+" Min")
            }
        }

        Row(
            modifier = Modifier
                .padding(top = 5.dp)
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(R.drawable.ic_time_clock),
                contentDescription = "Movie Time"
            )
            Text(modifier = Modifier.padding(start = 10.dp), text = "120 Min")
        }

        Text(modifier = Modifier.padding(start = 10.dp), text = "Overview")
        Text(modifier = Modifier.padding(start = 10.dp), text = "Test details")

        Text(modifier = Modifier.padding(start = 10.dp), text = "Cast")
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            /*movie?.genres?.forEach { genre ->
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
            }*/
        }
    }
}


@Composable
@Preview
private fun MovieDetailsPreview() {
    ShowMovieDetailsScreen(
        viewState = MovieDetailsViewState()
    )
}


