package com.elkite.presentation.video

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.elkite.domain.model.Video
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


//Main screen of the app, shows a list of videos
@Composable
fun VideoScreen(
    modifier: Modifier = Modifier,
    viewModel: VideoViewModel = viewModel(),
    navController: NavController,
) {
    val videos: LazyPagingItems<Video> = viewModel.videosState.collectAsLazyPagingItems()

    Column(modifier = modifier) {
        VideoList(videos, onItemClick = { video ->
            val encodedUrl = URLEncoder.encode(video.url, StandardCharsets.UTF_8.toString())
            navController.navigate("videoPlayer/$encodedUrl")
        })
    }
}