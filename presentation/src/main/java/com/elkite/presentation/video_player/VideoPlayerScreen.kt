package com.elkite.presentation.video_player

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.elkite.presentation.common.VideoPlayer

@Composable
fun VideoPlayerScreen(videoUrl: String, navController: NavController, modifier: Modifier = Modifier) {

    Column(modifier = modifier) {
        //TODO VideoPlayer doesn't support other format beside m3u8
        //VideoPlayer(uri = Uri.parse(videoUrl))
        VideoPlayer(
            onExit = {
                navController.popBackStack()
            }
        )
    }
}