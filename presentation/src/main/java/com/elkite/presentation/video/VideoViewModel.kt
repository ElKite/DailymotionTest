package com.elkite.presentation.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.elkite.domain.model.Video
import com.elkite.domain.repository.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class VideoViewModel @Inject constructor(
    private val videoRepository: VideoRepository
) : ViewModel() {

    private val _videosState: MutableStateFlow<PagingData<Video>> =
        MutableStateFlow(PagingData.empty())
    val videosState = _videosState

    init {
        getPagedVideos()
    }

    private fun getPagedVideos() {
        viewModelScope.launch {
            videoRepository.getPagedVideos().distinctUntilChanged().cachedIn(viewModelScope)
                .collect { _videosState.value = it }
        }
    }
}