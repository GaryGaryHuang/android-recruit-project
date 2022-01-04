package com.hahow.util

import java.util.*
import kotlin.math.abs

//  return days
fun Calendar.calculateDayDifference(other: Calendar): Int {
    val difference = abs(this.timeInMillis - other.timeInMillis)
    return (difference / (1000 * 60 * 60 * 24)).toInt()
}