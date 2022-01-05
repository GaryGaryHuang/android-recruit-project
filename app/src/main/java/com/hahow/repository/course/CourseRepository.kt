package com.hahow.repository.course

import com.hahow.database.entity.DatabaseCourse
import com.hahow.network.data.ApiCourseResponse

interface CourseRepository {
    suspend fun getCourseByAPI(): ApiCourseResponse
    suspend fun getCourseByDatabase(): List<DatabaseCourse>
}