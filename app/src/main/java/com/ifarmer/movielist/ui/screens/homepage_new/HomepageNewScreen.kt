package com.ifarmer.movielist.ui.screens.homepage_new

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import androidx.compose.foundation.lazy.itemsIndexed
import com.ifarmer.movielist.ui.components.MovieListItem


@Composable
fun ShowMovieList(
    navController: NavHostController,
    viewState: StateFlow<HomepageNewViewState>,
    viewEvent: (HomepageNewViewEvent) -> Unit
){
    val homePageViewState by viewState.collectAsStateWithLifecycle()

    ShowMovieDataList(movieList = homePageViewState.movieList, viewEvent = viewEvent)


}


@Composable
fun ShowMovieDataList(
    movieList: List<MovieWithWishlistEntities> = listOf(),
    viewEvent: (HomepageNewViewEvent) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
//        CustomNewMovieGridView(
//            movieWishList = movieList,
//            onItemClick = { },
//            onFavoriteClick = {
//                viewEvent(HomepageNewViewEvent.OnFavorite(it!!))
//            }
//        )

        LazyVerticalGrid (
            columns = GridCells.Fixed(2)
        ) {
            itemsIndexed(movieList) { index, movieList ->
                MovieListItem(
                    movieWishData = movieList,
                    onItemClick = { },
                    onFavoriteClick = {
                        Log.e("TAG", "ShowMovieDataList: "+index )
                        viewEvent(HomepageNewViewEvent.OnFavorite(index))
                    }
                )
            }
        }
    }
}