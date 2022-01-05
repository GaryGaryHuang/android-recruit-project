package com.hahow.util

import android.content.Context
import java.io.BufferedReader
import java.util.*
import kotlin.math.abs

//  return days
fun Calendar.calculateDayDifference(other: Calendar): Int {
    val difference = abs(this.timeInMillis - other.timeInMillis)
    return (difference / (1000 * 60 * 60 * 24)).toInt()
}

fun Context.loadJsonFromAsset(fileName: String): String? {
    return try {
        assets.open(fileName).bufferedReader().use(BufferedReader::readText)
    } catch (e: Exception) {
        null
    }
}