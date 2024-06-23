package com.elkite.presentation.video

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.elkite.domain.model.Video
import com.elkite.presentation.common.state.ErrorScreen
import com.elkite.presentation.common.state.LoadingScreen

@Composable
fun VideoList(
    videos: LazyPagingItems<Video>,
    onItemClick: (Video) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        item {
            Text(text = "Welcome !", style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(16.dp)  )
        }
        items(videos.itemCount) { key ->
            videos[key]?.let {
                VideoItem(it, onItemClick)
                if (key != videos.itemCount - 1) Spacer(modifier = Modifier.height(8.dp))
            }
        }
        videos.apply {
            when {
                loadState.refresh is LoadState.Loading -> item { LoadingScreen(Modifier.fillMaxSize()) }
                loadState.hasError -> item {
                    ErrorScreen(
                        message = "We are sorry, an error occurred.",
                        buttonMsg = "Retry",
                        buttonAction = { retry() }
                    )
                }
            }
        }
    }
}