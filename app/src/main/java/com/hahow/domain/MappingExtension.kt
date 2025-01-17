package com.hahow.domain

import com.hahow.network.data.ApiCourseResponse
import java.text.SimpleDateFormat
import java.util.*

fun ApiCourseResponse.toDomainCourseList(): List<Course> {
    return data.map {
        val status = mapCourseStatus(it.status)
        val student = if (status == CourseStatus.Published) it.numSoldTickets else 0
        val proposalDueCalendar = it.proposalDueTime?.run {
            val format = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'", Locale.getDefault())
            val date = format.parse(this) ?: return@run null
            Calendar.getInstance().also { calendar ->
                calendar.timeInMillis = date.time + TimeZone.getDefault().rawOffset
            }
        }

        Course(
            it.title,
            it.coverImageUrl,
            status,
            100f * it.numSoldTickets / it.successCriteria.numSoldTickets,
            student,
            proposalDueCalendar,
            it.totalVideoLengthInSeconds ?: 0
        )
    }
}

private fun mapCourseStatus(status: String): CourseStatus {
    return when(status) {
        "INCUBATING" -> CourseStatus.Fundraising
        "SUCCESS" -> CourseStatus.Success
        "PUBLISHED" -> CourseStatus.Published
        else -> CourseStatus.Unknown
    }
}