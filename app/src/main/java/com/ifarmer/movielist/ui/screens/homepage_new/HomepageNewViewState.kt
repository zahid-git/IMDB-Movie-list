package com.ifarmer.movielist.ui.screens.homepage_new

import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieWithWishlistEntities
import com.ifarmer.movielist.data.model.response.MovieDataModel

data class HomepageNewViewState(
    var movieList: List<MovieWithWishlistEntities> = listOf()
)