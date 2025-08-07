package com.ifarmer.movielist.ui.screens.homepage

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ifarmer.movielist.R
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.ui.components.CustomMovieGridView
import com.ifarmer.movielist.ui.components.CustomMovieListView
import com.ifarmer.movielist.ui.theme.MyIMBDModernMovieAppTheme


@Composable
fun HomePageScreen(
    navController: NavHostController,
    viewModel: HomepageViewModel
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val movieList = viewState.movieData?.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        movieList?.refresh()
    }

    MainHomepageScreen(movieList)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHomepageScreen(
    movieList: LazyPagingItems<MovieEntities>?
) {
    var isGrid by remember { mutableStateOf(true) }
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Item 1", "Item 2", "Item 3")

    MyIMBDModernMovieAppTheme {
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
                        IconButton(
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .size(35.dp),
                            onClick = { }) {
                            Icon(
                                modifier = Modifier.padding(5.dp),
                                painter = painterResource(R.drawable.ic_search),
                                contentDescription = "Toggle View"
                            )
                        }
                        IconButton(
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .size(35.dp),
                            onClick = { expanded = true }) {
                            Icon(
                                modifier = Modifier.padding(5.dp),
                                painter = painterResource(R.drawable.ic_filter),
                                contentDescription = "Toggle View"
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            options.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(item) },
                                    onClick = {

                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                )
            },
        ) { paddingValues -> // ← These padding values must be used
            Column(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(end = 10.dp, top = 10.dp, bottom = 10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 10.dp),
                        fontWeight = FontWeight.Bold,
                        text = "All Movies"
                    )

                    // List view icon
                    IconButton(
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .size(20.dp),
                        onClick = { if (isGrid) isGrid = false }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_list_view),
                            tint = if (!isGrid) Color.Red else Color.Black,
                            contentDescription = "Toggle View"
                        )
                    }

                    // Grid view icon
                    IconButton(
                        modifier = Modifier.size(20.dp),
                        onClick = { if (!isGrid) isGrid = true }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_grid_view),
                            tint = if (isGrid) Color.Red else Color.Black,
                            contentDescription = "Toggle View"
                        )
                    }
                }

                AnimatedContent(
                    targetState = isGrid,
                    transitionSpec = {
                        fadeIn(animationSpec = tween(300)) togetherWith
                                fadeOut(animationSpec = tween(300))
                    },
                    label = "List/Grid Switch"
                ) { targetIsGrid ->
                    if (movieList?.loadState?.refresh !is LoadState.Loading) {
                        when (targetIsGrid) {
                            true -> CustomMovieGridView(movies = movieList)
                            false -> CustomMovieListView(movies = movieList)
                        }
                    }
                }

            }
        }
    }
}

@Composable
@Preview
fun HomepagePreview() {
    MainHomepageScreen(
        movieList = null
    )
}