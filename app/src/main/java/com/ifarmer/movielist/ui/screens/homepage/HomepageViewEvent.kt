package com.ifarmer.movielist.ui.screens.homepage

import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieWithWishlistEntities

sealed class HomepageViewEvent {
    data class changeGenre(val genreName: String) : HomepageViewEvent()
    data class goToMovieDetails(val movieId: Int) : HomepageViewEvent()
    data class changeListType(val isGrid: Boolean) : HomepageViewEvent()
    data class showGenre(val isExpand: Boolean) : HomepageViewEvent()
    data class changeSerachValue(val search: String) : HomepageViewEvent()
    data class addToFavoriteItem(val movieId: Int) : HomepageViewEvent()


}