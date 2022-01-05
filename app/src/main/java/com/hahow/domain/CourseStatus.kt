package com.hahow.domain

sealed class CourseStatus
{
    object Fundraising: CourseStatus()
    object Success: CourseStatus()
    object Published: CourseStatus()
    object Unknown: CourseStatus()
}
