package com.elkite.data.video.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.elkite.data.video.network.model.VideoJson


/**
 * This is used by the Paging 3 library to fetch the videos from the network using the @VideoDailymotionApi
 */
class VideoPagingSource(
    private val videoDailymotionApi: VideoDailymotionApi,
) : PagingSource<Int, VideoJson>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideoJson> {
        val page = params.key ?: 1

        try {
            val response = videoDailymotionApi.getVideos(page = page)
            val nextPage = if (response.list.isNotEmpty()) page + 1 else null
            return LoadResult.Page(
                data = response.list,
                prevKey = null,
                nextKey = nextPage
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, VideoJson>): Int? {
        return state.anchorPosition
    }
}