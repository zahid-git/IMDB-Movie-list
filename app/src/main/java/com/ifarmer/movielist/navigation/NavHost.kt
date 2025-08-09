package com.ifarmer.movielist.navigation


import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.ifarmer.movielist.ui.screens.homepage.HomePageScreen
import com.ifarmer.movielist.ui.screens.homepage.HomepageViewModel
import com.ifarmer.movielist.ui.screens.moviedetails.MovieDetailsScreen
import com.ifarmer.movielist.ui.screens.moviedetails.MovieDetailsViewModel
import com.ifarmer.movielist.ui.screens.splash.SplashScreen
import com.ifarmer.movielist.ui.screens.splash.SplashScreenViewModel
import com.ifarmer.movielist.utils.Constants

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavRoutes.SplashScreen,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(
                    durationMillis = Constants.SCREEN_TRANSITION_DURATION,
                    delayMillis = Constants.SCREEN_TRANSITION_DELAY
                )
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(
                    durationMillis = Constants.SCREEN_TRANSITION_DURATION,
                    delayMillis = Constants.SCREEN_TRANSITION_DELAY
                )
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(
                    durationMillis = Constants.SCREEN_TRANSITION_DURATION,
                    delayMillis = Constants.SCREEN_TRANSITION_DELAY
                )
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(
                    durationMillis = Constants.SCREEN_TRANSITION_DURATION,
                    delayMillis = Constants.SCREEN_TRANSITION_DELAY
                )
            )
        }
    ) {
        composable<NavRoutes.SplashScreen> {
            val viewModel: SplashScreenViewModel = hiltViewModel()
            SplashScreen(navController = navController, viewModel = viewModel)
        }
        composable<NavRoutes.HomepageScreen> {
            val homeViewModel: HomepageViewModel = hiltViewModel()
            HomePageScreen(
                navController = navController,
                viewState = homeViewModel.viewState,
                onEvent = homeViewModel::onEvent,
                viewAction = homeViewModel.action
            )
        }
        composable<NavRoutes.MovieDetailsScreen> {
            val movieId = it.toRoute<NavRoutes.MovieDetailsScreen>().movieId
            val homeViewModel: MovieDetailsViewModel = hiltViewModel()
            MovieDetailsScreen(
                navController = navController,
                movieId = movieId,
                viewState = homeViewModel.viewState,
                viewAction = homeViewModel.viewAction,
                onEvent = homeViewModel::onEvent
            )

        }
    }
}
