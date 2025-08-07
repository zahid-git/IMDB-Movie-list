package com.ifarmer.movielist.ui.screens.homepage

sealed class HomepageViewEvent {
    data class changeGenre(val genreName: String) : HomepageViewEvent()

}