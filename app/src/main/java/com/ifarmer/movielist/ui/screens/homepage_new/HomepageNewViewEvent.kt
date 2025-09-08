package com.ifarmer.movielist.ui.screens.homepage_new

sealed class HomepageNewViewEvent() {
    data class OnFavorite( val index: Int) : HomepageNewViewEvent()
}