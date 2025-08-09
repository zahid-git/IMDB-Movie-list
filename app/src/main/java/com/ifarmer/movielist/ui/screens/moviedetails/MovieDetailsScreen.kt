package com.ifarmer.movielist.ui.screens.moviedetails

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ifarmer.movielist.R
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MovieDetailsScreen(
    navController: NavHostController,
    movieId: Int,
    viewState: StateFlow<MovieDetailsViewState>,
    viewAction: SharedFlow<MovieDetailsAction>,
    onEvent: (MovieDetailsViewEvent) -> Unit
) {
    val movieDetailsState by viewState.collectAsStateWithLifecycle()

    LaunchedEffect(viewAction) {
        viewAction.collect {
            when(it) {
                MovieDetailsAction.GoToPreviousScreen -> {
                    navController.popBackStack()
                }
            }
        }
    }

    onEvent(MovieDetailsViewEvent.fetchMovieData(movieId))

    ShowMovieDetailsScreen(viewState = movieDetailsState, onEvent = onEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShowMovieDetailsScreen(
    viewState: MovieDetailsViewState,
    onEvent: (MovieDetailsViewEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Movie App"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF067520),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                actions = {

                },
                navigationIcon = {
                    IconButton(onClick = {
                        onEvent(MovieDetailsViewEvent.goToPreviousPage)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowLeft,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(paddingValue)
                .verticalScroll(rememberScrollState())
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Spacer(Modifier.padding(top = 30.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier
                        .height(300.dp)
                        .wrapContentWidth(),
                    model = viewState.image,
                    contentDescription = "Movie Image",
                    placeholder = painterResource(R.drawable.placeholder),
                    error = painterResource(R.drawable.placeholder)
                )
            }
            Text(
                modifier = Modifier.padding(top = 30.dp),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                text = viewState.movieName
            )
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                viewState.genres.forEach { genre ->
                    Card(
                        modifier = Modifier
                            .wrapContentWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFD3D3D3), // Light cyan background
                            contentColor = Color.Black // Text/Icon color
                        ),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(1.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp),
                            text = genre,
                            fontSize = 10.sp
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(15.dp),
                        painter = painterResource(R.drawable.ic_calendar),
                        contentDescription = "Calender"
                    )
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        fontSize = 12.sp,
                        text = viewState.releaseYear
                    )
                }

                Row(
                    modifier = Modifier
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(15.dp),
                        painter = painterResource(R.drawable.ic_time_clock),
                        contentDescription = "Movie Time"
                    )
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        fontSize = 12.sp,
                        text = viewState.movieDuration
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(15.dp),
                    painter = painterResource(R.drawable.ic_person_group),
                    contentDescription = "Movie Time"
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    fontSize = 12.sp,
                    text = viewState.directorName
                )
            }

            Text(
                modifier = Modifier.padding(top = 30.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                text = stringResource(R.string.overview)
            )
            Text(
                modifier = Modifier.padding(top = 10.dp),
                fontSize = 16.sp,
                text = viewState.overview
            )

            Text(
                modifier = Modifier.padding(top = 30.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                text = stringResource(R.string.cast)
            )
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                viewState.actors.forEach { genre ->
                    Card(
                        modifier = Modifier
                            .wrapContentWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFFFFF),
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(1.dp)
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


@Composable
@Preview
private fun MovieDetailsPreview() {
    ShowMovieDetailsScreen(
        viewState = MovieDetailsViewState(
            movieName = "Test Movie",
            releaseYear = "2002",
            movieDuration = "120 Min",
            directorName = "John Doe",
            overview = "A National Parks Service agent investigates a brutal death at Yosemite National Park.",
            actors = arrayListOf("Actor 1", "Actor 2", "Actor 3", "Actor 4", "Actor 5", "Actor 6"),
            genres = arrayListOf("Drama", "Crime", "Comedy")
        ),
        onEvent = { }
    )
}


