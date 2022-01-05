package com.hahow.view.ui.overview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hahow.android_recruit_project.R
import com.hahow.domain.Course
import com.hahow.domain.CourseStatus
import com.hahow.domain.toDomainCourseList
import com.hahow.repository.course.CourseRepository
import com.hahow.util.isNetworkConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OverviewViewModel(application: Application, private val courseRepository: CourseRepository) : AndroidViewModel(application) {
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
        if (getApplication<Application>().isNetworkConnected) {
            courseRepository.getCourseByAPI().run {
                val domainData = this.toDomainCourseList().filter { it.status != CourseStatus.Unknown }
                _courseList.postValue(domainData)
            }
        }
        else {
            _message.postValue(getApplication<Application>().getString(R.string.network_disconnected))
        }
        _isLoading.postValue(false)
    }
}