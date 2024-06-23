package com.elkite.data.video.network

import com.elkite.data.video.network.model.VideoListJson
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoDailymotionApi {

    @GET("videos")
    suspend fun getVideos(
        @Query("channel") channel: String = "tech",
        @Query("language") language: String = "en",
        @Query("fields") fields: String = "id,title,thumbnail_1080_url,description,views_total,owner.avatar_720_url,uploaded_time,likes_total,owner.nickname,duration,url",
        @Query("page") page: Int = 1
    ): VideoListJson
}