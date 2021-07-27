package com.raindragonn.favoritetracklist.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raindragonn.favoritetracklist.data.model.TrackItem
import com.raindragonn.favoritetracklist.data.repository.TrackRepository

abstract class BaseViewModel(
    private val repository: TrackRepository
) : ViewModel() {
    // 로딩을 위한 LiveData
    protected val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    // 에러감지를 위한 LiveData
    protected val _isError: MutableLiveData<Boolean> = MutableLiveData(false)
    val isError: LiveData<Boolean>
        get() = _isError

    fun changeStateLoading(loading: Boolean) {
        if (loading) {
            showLoading()
        } else {
            dismissLoading()
        }
    }

    fun changeStateError(hasError: Boolean) {
        if (hasError) {
            showError()
        } else {
            dismissError()
        }
    }

    fun showLoading() {
        _isLoading.value = true
    }

    fun dismissLoading() {
        _isLoading.value = false
    }

    fun showError() {
        dismissLoading()
        _isError.value = true
    }

    fun dismissError() {
        _isError.value = false
    }

    // DB에 해당 노래정보를 변경
    suspend fun setFavorite(trackItem: TrackItem) {
        if (hasData(trackItem)) {
            deleteFavorite(trackItem)
        } else {
            insertFavorite(trackItem.copy(isFavorite = true))
        }
    }

    // DB에 해당 좋아요 정보가 있는지 확인
    private suspend fun hasData(trackItem: TrackItem): Boolean =
        repository.getFavoriteById(trackItem.trackId) != null

    // DB에 해당 좋아요 정보 삭제
    private suspend fun deleteFavorite(trackItem: TrackItem) {
        repository.deleteFavorite(trackItem.trackId)
    }

    // DB에 해당 좋아요 정보 삽입
    private suspend fun insertFavorite(trackItem: TrackItem) {
        repository.insertFavorite(trackItem)
    }
}