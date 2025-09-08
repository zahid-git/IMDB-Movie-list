package com.ifarmer.movielist.ui.screens.homepage_new

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieWithWishlistEntities
import com.ifarmer.movielist.ui.components.CustomNewMovieGridView
import kotlinx.coroutines.flow.StateFlow


@Composable
fun ShowMovieList(
    navController: NavHostController,
    viewState: StateFlow<HomepageNewViewState>
){
    val homePageViewState by viewState.collectAsStateWithLifecycle()

    ShowMovieDataList(movieList = homePageViewState.movieList)


}


@Composable
@Preview
fun ShowMovieDataList(
    movieList: List<MovieWithWishlistEntities> = listOf()
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        CustomNewMovieGridView(
            movieWishList = movieList,
            onItemClick = { },
            onFavoriteClick = { }
        )
    }
}