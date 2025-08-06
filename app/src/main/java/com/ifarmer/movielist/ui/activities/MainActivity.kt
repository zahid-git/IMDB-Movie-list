package com.ifarmer.movielist.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.ifarmer.movielist.navigation.AppNavHost
import com.ifarmer.movielist.ui.theme.MyIMBDModernMovieAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var isLoading = true

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { isLoading }
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            isLoading = false
        }

        enableEdgeToEdge()
        setContent {
            MyIMBDModernMovieAppTheme {
                AppNavHost(modifier = Modifier, navController = rememberNavController())
            }
        }

    }
}

