package com.ifarmer.movielist.ui.screens.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.ui.components.CustomMovieGridView
import com.ifarmer.movielist.ui.components.CustomMovieListView


@Composable
fun HomePageScreen(
    navController: NavHostController,
    viewModel: HomepageViewModel
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val movieList = viewModel.getMovieList.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        movieList.refresh()
    }

    MainHomepageScreen(movieList)
}

@Composable
fun MainHomepageScreen(
    movieList: LazyPagingItems<MovieEntities>?
) {

    var isGrid by remember { mutableStateOf(false) }

    MaterialTheme {
        Column {
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(modifier = Modifier.padding(top = 50.dp),
                    onClick = { isGrid = !isGrid }) {
                    Icon(
                        imageVector = if (isGrid) Icons.Default.AccountCircle else Icons.Default.ThumbUp,
                        contentDescription = "Toggle View"
                    )
                }
            }

            if (movieList?.loadState?.refresh !is LoadState.Loading) {
                when(isGrid) {
                    true -> CustomMovieGridView(movies = movieList)
                    false -> CustomMovieListView(movies = movieList)
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