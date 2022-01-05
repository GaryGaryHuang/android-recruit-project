package com.hahow.domain

import java.util.*

data class Course(
    val name: String,
    val coverUrl: String,
    val status: CourseStatus,
    val goalPercentage: Float,
    val student: Int,
    val proposalDueTime: Calendar?,
    val totalVideoLengthInSeconds: Int = 0,
)
