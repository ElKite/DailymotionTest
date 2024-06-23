package com.elkite.domain.repository

import androidx.paging.PagingData
import com.elkite.domain.model.Video
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    fun getPagedVideos(): Flow<PagingData<Video>>
}