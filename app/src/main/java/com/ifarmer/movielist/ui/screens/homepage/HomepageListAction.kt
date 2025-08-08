package com.ifarmer.movielist.ui.screens.homepage

sealed class HomepageListAction {
    data class NavigateToDetail(val movieId: Int) : HomepageListAction()
}