package com.elkite.presentation.video

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.elkite.domain.model.Video
import com.elkite.utils.formatDuration
import com.elkite.utils.formatTimestamp

@Composable
fun VideoItem(video: Video, onItemClick: (Video) -> Unit, modifier: Modifier = Modifier) {

    Card(modifier = modifier.fillMaxWidth(),
        onClick = {
            onItemClick(video)
        }) {
        Column {
            Thumbnail(video)
            Spacer(modifier = Modifier.height(8.dp))
            Description(video)
        }
    }
}

@Composable
private fun Description(video: Video) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = video.title, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = video.description,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(video.ownerAvatarUrl)
                    .build(),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(48.dp),

                )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = video.ownerNickname, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
private fun Thumbnail(video: Video) {
    Box {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(video.thumbnailUrl)
                .build(),
            contentDescription = "thumbnail",
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp),
            text = video.uploadedTime.formatTimestamp(),
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp),
            text = video.duration.formatDuration(),
            style = MaterialTheme.typography.labelSmall
        )
    }
}