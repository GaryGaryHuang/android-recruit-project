package com.hahow.view.ui.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hahow.repository.course.CourseRepository
import com.hahow.view.ui.overview.OverviewViewModel

class OverviewModelFactory(private val application: Application, private val courseCourseRepository: CourseRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OverviewViewModel(application, courseCourseRepository) as T
    }
}
