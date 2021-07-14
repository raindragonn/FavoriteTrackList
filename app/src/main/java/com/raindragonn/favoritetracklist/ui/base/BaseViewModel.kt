package com.raindragonn.favoritetracklist.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    // 로딩을 위한 LiveData
    protected val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    // 에러감지를 위한 LiveData
    protected val _isError: MutableLiveData<Boolean> = MutableLiveData(false)
    val isError: LiveData<Boolean>
        get() = _isError

    fun startLoading() {
        _isLoading.value = true
    }

    fun stopLoading() {
        _isLoading.value = false
    }

    fun startError() {
        stopLoading()
        _isError.value = true
    }

    fun stopError() {
        _isError.value = false
    }

}