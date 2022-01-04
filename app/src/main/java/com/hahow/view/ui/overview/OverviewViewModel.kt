package com.hahow.view.ui.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hahow.domain.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {
    private val _courseList: MutableLiveData<List<Course>> = MutableLiveData(mutableListOf())
    val courseList: LiveData<List<Course>> = _courseList
    private val _message: MutableLiveData<String> = MutableLiveData()
    val message: LiveData<String> = _message
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getCourse()
    }

    fun getCourse() = viewModelScope.launch(Dispatchers.Default) {
        _isLoading.postValue(true)
        _isLoading.postValue(false)
    }
}