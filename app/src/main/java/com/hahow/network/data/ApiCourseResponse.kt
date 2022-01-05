package com.hahow.network.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiCourseResponse(
    @field:Json(name = "data")
    val data: List<ApiCourse> = mutableListOf()
)

@JsonClass(generateAdapter = true)
data class ApiCourse(
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "coverImageUrl")
    val coverImageUrl: String,
    @field:Json(name = "status")
    val status: String,
    @field:Json(name = "successCriteria")
    val successCriteria: CourseCriteria,
    @field:Json(name = "numSoldTickets")
    val numSoldTickets: Int,
    @field:Json(name = "proposalDueTime")
    val proposalDueTime: String?,
    @field:Json(name = "totalVideoLengthInSeconds")
    val totalVideoLengthInSeconds: Int?,
)

@JsonClass(generateAdapter = true)
data class CourseCriteria(
    @field:Json(name = "numSoldTickets")
    val numSoldTickets: Int
)
