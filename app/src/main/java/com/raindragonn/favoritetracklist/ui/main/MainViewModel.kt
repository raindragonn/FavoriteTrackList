package com.raindragonn.favoritetracklist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.raindragonn.favoritetracklist.data.model.TrackItem
import com.raindragonn.favoritetracklist.data.repository.TrackRepository
import com.raindragonn.favoritetracklist.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: TrackRepository
) : BaseViewModel() {

    private val _trackList = MutableLiveData<List<TrackItem>>()
    val trackList: LiveData<List<TrackItem>>
        get() = _trackList

    fun getList() = viewModelScope.launch {
        startLoading()
        _trackList.value = repository.getTrackList()
        stopLoading()
    }
}