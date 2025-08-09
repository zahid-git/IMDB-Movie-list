package com.ifarmer.movielist.ui.screens.moviedetails

import com.ifarmer.movielist.ui.screens.homepage.HomepageViewEvent

sealed class MovieDetailsViewEvent {
    data class fetchMovieData(val movieId: Int) : MovieDetailsViewEvent()
    data object goToPreviousPage : MovieDetailsViewEvent()

}