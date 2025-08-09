package com.ifarmer.movielist.ui.screens.homepage

sealed class HomepageViewEvent {
    data class changeGenre(val genreName: String) : HomepageViewEvent()
    data class goToMovieDetails(val movieId: Int) : HomepageViewEvent()
    data class changeListType(val isGrid: Boolean) : HomepageViewEvent()
    data class showGenre(val isExpand: Boolean) : HomepageViewEvent()
    data class changeSerachValue(val search: String) : HomepageViewEvent()


}