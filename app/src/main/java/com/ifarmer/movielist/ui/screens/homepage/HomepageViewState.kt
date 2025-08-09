package com.ifarmer.movielist.ui.screens.homepage

import androidx.paging.PagingData
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieGenresEntities
import kotlinx.coroutines.flow.Flow

data class HomepageViewState (
    var title: String = "" ,
    var searchValue: String = "" ,
    var selectedGenre: String = "" ,
    var isGrid: Boolean = false,
    var isGenreShow: Boolean = false,
    var movieData: Flow<PagingData<MovieEntities>>? = null,
    var movieGenres: List<MovieGenresEntities> = arrayListOf()

)