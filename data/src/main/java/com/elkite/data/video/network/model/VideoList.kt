package com.elkite.data.video.network.model

data class VideoListJson(
    val page: Int,
    val limit: Int,
    val explicit: Boolean,
    val total: Int,
    val has_more: Boolean,
    val list: List<VideoJson>
)