package com.hahow.domain

sealed class CourseStatus
{
    object Fundraising: CourseStatus()
    object Success: CourseStatus()
    object Completed: CourseStatus()
    object Published: CourseStatus()
}
