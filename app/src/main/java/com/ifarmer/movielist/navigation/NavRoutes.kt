package com.ifarmer.movielist.navigation

import kotlinx.serialization.Serializable

data object NavRoutes {
    @Serializable object SplashScreen
    @Serializable object HomepageScreen
    @Serializable data class MovieDetailsScreen(val movieId: Int)
}