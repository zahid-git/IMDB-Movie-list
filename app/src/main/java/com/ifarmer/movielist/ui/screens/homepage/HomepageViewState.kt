package com.ifarmer.movielist.ui.screens.homepage

import androidx.paging.PagingData
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import kotlinx.coroutines.flow.Flow

data class HomepageViewState (
    var isGrid: Boolean = false,
    var isGenreShow: Boolean = false,
    var movieData: Flow<PagingData<MovieEntities>>? = null

)