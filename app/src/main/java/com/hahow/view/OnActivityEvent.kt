package com.hahow.view

import com.hahow.repository.course.CourseRepository

interface OnActivityEvent {
    fun getCourseRepository(): CourseRepository
}