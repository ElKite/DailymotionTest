package com.elkite.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elkite.presentation.video.VideoScreen
import com.elkite.presentation.video.VideoViewModel
import com.elkite.presentation.video_details.VideoDetailsScreen

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "video") {
        composable("video") {
            val viewModel = hiltViewModel<VideoViewModel>()
            VideoScreen(viewModel = viewModel, navController = navController, modifier = modifier)
        }
        composable("videoPlayer/{url}") {
            val url = it.arguments?.getString("url")

            url?.let {
                VideoDetailsScreen(videoUrl = url, modifier = modifier, navController = navController)
            }
        }
    }
}