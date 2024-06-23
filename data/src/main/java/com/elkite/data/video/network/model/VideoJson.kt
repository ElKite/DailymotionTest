package com.elkite.data.video.network.model

import com.elkite.domain.model.Video
import com.google.gson.annotations.SerializedName


data class VideoJson (
    val id: String,
    val title: String?,
    val description: String?,
    @SerializedName("thumbnail_1080_url")
    val thumbnail_url: String?,
    val uploaded_time: Long?,

    val url: String?,
    @SerializedName("owner.avatar_720_url")
    val `owner_avatar_720_url` : String?,
    @SerializedName("owner.nickname")
    val owner_nickname: String?,

    val views_total: Long?,
    val likes_total: Long?,
    val duration: Long?
)

fun VideoJson.toEntity() =
    Video(
        id = this.id,
        title = this.title ?: "",
        description = this.description ?: "",
        thumbnailUrl = this.thumbnail_url ?: "",
        ownerAvatarUrl = this.owner_avatar_720_url ?: "",
        ownerNickname = this.owner_nickname ?: "",
        uploadedTime = this.uploaded_time ?: 0,
        viewsTotal = this.views_total ?: 0,
        likesTotal = this.likes_total ?: 0,
        url = this.url ?: "",
        duration = this.duration ?: 0
    )

fun List<VideoJson>.toEntity() = this.map { it.toEntity() }