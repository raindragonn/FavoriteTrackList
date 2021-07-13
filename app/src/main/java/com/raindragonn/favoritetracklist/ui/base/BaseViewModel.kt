package com.raindragonn.favoritetracklist.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    protected val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun startLoading() {
        _isLoading.value = true
    }

    fun stopLoading() {
        _isLoading.value = false
    }
}