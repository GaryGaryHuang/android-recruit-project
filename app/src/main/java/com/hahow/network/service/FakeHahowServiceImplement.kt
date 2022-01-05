package com.hahow.network.service

import com.hahow.network.data.ApiCourseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class FakeHahowServiceImplement: HahowService {
    private var courseResponse: ApiCourseResponse = ApiCourseResponse()

    fun setCourse(courseResponse: ApiCourseResponse) {
        this.courseResponse = courseResponse
    }

    override suspend fun getCourse(): ApiCourseResponse = withContext(Dispatchers.Default) {
        delay(3000)
        return@withContext courseResponse
    }
}