package com.ifarmer.movielist.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.ifarmer.movielist.R
import com.ifarmer.movielist.navigation.NavRoutes

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashScreenViewModel
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { action ->
            when (action) {
                SplashScreenViewEvent.FetchMovieData -> viewModel.fetchMovieData()
                SplashScreenViewEvent.GoToHomePage -> {
                    navController.navigate(NavRoutes.HomepageScreen) {
                        popUpTo(NavRoutes.SplashScreen) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getDataAndStore()
    }

    MainSplashScreen(
        isLoading = viewState.isLoading,
        errorMessage = viewState.errorMessage,
        viewModel::getDataAndStore
    )
}


@Composable
private fun MainSplashScreen(
    isLoading: Boolean,
    errorMessage: String?,
    tryAgain: ()-> Unit
) {

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(150.dp)
        )
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(30.dp)
                    .padding(top = 50.dp)
            )
        } else {
            errorMessage.let {
                Text(
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp, top = 20.dp),
                    color = Color.Red,
                    text = errorMessage.toString(),
                    textAlign = TextAlign.Center
                )
                Button(
                    modifier = Modifier.padding(top = 20.dp),
                    onClick = {
                        tryAgain()
                    }
                ) {
                    Text(
                        text = "Retry"
                    )
                }
            }
        }
    }

}


@Composable
@Preview
private fun SplashScreenPreview() {
    MainSplashScreen(
        isLoading = false,
        errorMessage = "Fail to fetch data",
        tryAgain = { }
    )
}