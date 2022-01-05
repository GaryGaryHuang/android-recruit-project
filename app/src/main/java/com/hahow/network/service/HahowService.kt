package com.hahow.network.service

import com.hahow.network.data.ApiCourseResponse
import com.hahow.network.endpoint.HahowEndpointPath
import retrofit2.http.GET

interface HahowService {
    @GET(HahowEndpointPath.Course.PATH)
    suspend fun getCourse(): ApiCourseResponse
}