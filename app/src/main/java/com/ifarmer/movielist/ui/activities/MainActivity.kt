package com.ifarmer.movielist.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ifarmer.movielist.navigation.AppNavHost
import com.ifarmer.movielist.ui.theme.MyIMBDModernMovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyIMBDModernMovieAppTheme {
                AppNavHost(modifier = Modifier, navController = rememberNavController())
            }
        }
    }
}