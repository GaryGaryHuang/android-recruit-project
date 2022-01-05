package com.hahow.util

import android.content.Context
import java.io.BufferedReader
import java.util.*
import kotlin.math.abs

//  return hours
fun Calendar.calculateHourDifference(other: Calendar): Int {
    val difference = abs(this.timeInMillis - other.timeInMillis)
    return (difference / (1000f * 60 * 60)).toInt()
}

fun Context.loadJsonFromAsset(fileName: String): String? {
    return try {
        assets.open(fileName).bufferedReader().use(BufferedReader::readText)
    } catch (e: Exception) {
        null
    }
}