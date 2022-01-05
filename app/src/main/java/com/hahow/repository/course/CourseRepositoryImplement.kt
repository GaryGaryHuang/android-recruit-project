package com.hahow.repository.course

import com.hahow.database.entity.DatabaseCourse
import com.hahow.database.table.CourseTable
import com.hahow.network.data.ApiCourseResponse
import com.hahow.network.service.HahowService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CourseRepositoryImplement(private val networkService: HahowService, private val databaseTable: CourseTable): CourseRepository
{
    override suspend fun getCourseByAPI(): ApiCourseResponse = withContext(Dispatchers.Default) {
        return@withContext networkService.getCourse()
    }

    override suspend fun getCourseByDatabase(): List<DatabaseCourse> = withContext(Dispatchers.Default) {
        return@withContext databaseTable.getCourse()
    }
}