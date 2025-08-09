package com.ifarmer.movielist.ui.screens.homepage

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ifarmer.movielist.R
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieWithWishlistEntities
import com.ifarmer.movielist.navigation.NavRoutes
import com.ifarmer.movielist.ui.components.CustomMovieGridView
import com.ifarmer.movielist.ui.components.CustomMovieListView
import com.ifarmer.movielist.ui.screens.homepage.HomepageViewEvent.changeGenre
import com.ifarmer.movielist.ui.theme.MyIMBDModernMovieAppTheme
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow


@Composable
fun HomePageScreen(
    navController: NavHostController,
    viewState: StateFlow<HomepageViewState>,
    onEvent: (HomepageViewEvent) -> Unit,
    viewAction: SharedFlow<HomepageListAction>
) {
    val homePageViewState by viewState.collectAsStateWithLifecycle()
    val movieList = homePageViewState.movieData?.collectAsLazyPagingItems()

    LaunchedEffect(viewAction) {
        viewAction.collect { action ->
            when (action) {
                is HomepageListAction.NavigateToDetail -> {
                    navController.navigate(NavRoutes.MovieDetailsScreen(action.movieId))
                }
            }
        }
    }
    MainHomepageScreen(homePageViewState, movieList, onEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainHomepageScreen(
    viewState: HomepageViewState,
    movieList: LazyPagingItems<MovieWithWishlistEntities>?,
    onEvent: (HomepageViewEvent) -> Unit,
) {
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
                        Box(modifier = Modifier.padding(end = 5.dp).size(40.dp)) {
                            IconButton(onClick = { }) {
                                Icon(
                                    modifier = Modifier.size(30.dp),
                                    painter = painterResource(R.drawable.ic_favorite),
                                    contentDescription = "Notifications"
                                )
                            }
                            if (viewState.wishCount > 0) {
                                Box(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .size(18.dp)
                                        .align(Alignment.TopEnd)
                                        .offset(x = 10.dp, y = (-15).dp)
                                        .clip(CircleShape)
                                        .background(Color.Red),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = if (viewState.wishCount > 99) "99+" else viewState.wishCount.toString(),
                                        color = Color.White,
                                        fontSize = 8.sp,
                                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                        textAlign = TextAlign.Center,
                                        maxLines = 1,
                                    )
                                }
                            }
                        }
                        IconButton(
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .size(35.dp),
                            onClick = { onEvent(HomepageViewEvent.showGenre(true)) }) {
                            Icon(
                                modifier = Modifier.padding(5.dp),
                                painter = painterResource(R.drawable.ic_filter),
                                contentDescription = "Filter View"
                            )
                        }
                        DropdownMenu(
                            modifier = Modifier.height(300.dp),
                            expanded = viewState.isGenreShow,
                            onDismissRequest = { onEvent(HomepageViewEvent.showGenre(false)) }
                        ) {
                            DropdownMenuItem(
                                text = { Text("All") },
                                onClick = {
                                    onEvent(changeGenre(""))
                                    onEvent(HomepageViewEvent.showGenre(false))
                                }
                            )
                            viewState.movieGenres.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(item.name) },
                                    onClick = {
                                        onEvent(changeGenre(item.name))
                                        onEvent(HomepageViewEvent.showGenre(false))
                                    }
                                )
                            }
                        }
                    }
                )
            },
//            floatingActionButton = {
//                // TODO:: Light and Dark more related
//                FloatingActionButton(
//                    onClick = { }
//                ) {
//                    Icon(Icons.Rounded.Info, contentDescription = "Add")
//                }
//            }

        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
            ) {

                TextField(
                    value = viewState.searchValue,
                    onValueChange = { onEvent(HomepageViewEvent.changeSerachValue(it)) },
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
                    trailingIcon = {
                        if (viewState.searchValue.isNotEmpty()) {
                            Icon(
                                Icons.Filled.Close,
                                contentDescription = "Clear",
                                modifier = Modifier.clickable { onEvent(HomepageViewEvent.changeSerachValue("")) })
                        }
                    },
                    placeholder = { Text("Search...", color = Color.Gray) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE7F1F1), RoundedCornerShape(16.dp)),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )


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
                        text = viewState.title
                    )

                    // List view icon
                    IconButton(
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .size(20.dp),
                        onClick = {
                            if (viewState.isGrid) onEvent(
                                HomepageViewEvent.changeListType(
                                    false
                                )
                            )
                        }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_list_view),
                            tint = if (!viewState.isGrid) Color.Red else Color.Black,
                            contentDescription = "Toggle View"
                        )
                    }

                    // Grid view icon
                    IconButton(
                        modifier = Modifier.size(20.dp),
                        onClick = {
                            if (!viewState.isGrid) onEvent(
                                HomepageViewEvent.changeListType(
                                    true
                                )
                            )
                        }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_grid_view),
                            tint = if (viewState.isGrid) Color.Red else Color.Black,
                            contentDescription = "Toggle View"
                        )
                    }
                }

                AnimatedContent(
                    targetState = viewState.isGrid,
                    contentAlignment = Alignment.Center,
                    transitionSpec = {
                        fadeIn(animationSpec = tween(300)) togetherWith
                                fadeOut(animationSpec = tween(300))
                    },
                    label = "List/Grid Switch"
                ) { targetIsGrid ->
                    if (movieList?.loadState?.refresh !is LoadState.Loading) {
                        when (targetIsGrid) {
                            true -> CustomMovieGridView(
                                movieWishList = movieList,
                                onItemClick = { movieId ->
                                    movieId?.let { onEvent(HomepageViewEvent.goToMovieDetails(it)) }
                                },
                                onFavoriteClick = {movieId->
                                    movieId?.let { onEvent(HomepageViewEvent.addToFavoriteItem(it)) }
                                }
                            )

                            false -> CustomMovieListView(
                                movieWishList = movieList,
                                onItemClick = { movieId ->
                                    movieId?.let { onEvent(HomepageViewEvent.goToMovieDetails(it)) }
                                },
                                onFavoriteClick = {movieId->
                                    movieId?.let { onEvent(HomepageViewEvent.addToFavoriteItem(it)) }
                                }
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
private fun HomepagePreview() {
    MainHomepageScreen(
        viewState = HomepageViewState(
            wishCount = 100
        ),
        movieList = null,
        { },
    )
}