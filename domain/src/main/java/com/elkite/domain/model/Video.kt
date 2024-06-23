package com.elkite.domain.model

data class Video(
    val id: String,
    val title: String,
    val description: String,
    val uploadedTime: Long,
    val duration: Long,
    val url: String,

    val thumbnailUrl: String,
    val ownerAvatarUrl : String,
    val ownerNickname: String,

    val viewsTotal: Long,
    val likesTotal: Long,
)