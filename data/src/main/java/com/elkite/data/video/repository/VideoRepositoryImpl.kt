package com.elkite.data.video.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.elkite.data.video.network.VideoDailymotionApi
import com.elkite.data.video.network.VideoPagingSource
import com.elkite.data.video.network.model.toEntity
import com.elkite.domain.model.Video
import com.elkite.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val api: VideoDailymotionApi,
) : VideoRepository {

    override fun getPagedVideos(): Flow<PagingData<Video>> {
         return Pager(
             PagingConfig(pageSize = 10),
             pagingSourceFactory = { VideoPagingSource(api) }
         ).flow.map { pagingData ->
             pagingData.map {json ->
                json.toEntity()
             }
         }
    }
}
