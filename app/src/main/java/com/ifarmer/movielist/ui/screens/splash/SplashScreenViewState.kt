package com.ifarmer.movielist.ui.screens.splash

data class SplashScreenViewState(
    var isLoading: Boolean = false,
    var isRefreshing: Boolean = false,
    var errorMessage: String? = null
)