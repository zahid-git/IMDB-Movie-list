package com.ifarmer.movielist.ui.screens.moviedetails

sealed class MovieDetailsAction {
    data object GoToPreviousScreen : MovieDetailsAction()
}