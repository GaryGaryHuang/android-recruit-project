package com.hahow.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
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

val Context.isNetworkConnected : Boolean
    get() = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?)?.run {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork: Network = activeNetwork ?: return false
            val networkCapabilities = getNetworkCapabilities(activeNetwork) ?: return false
            when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    true
                }
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> { true }
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
        else {
            activeNetworkInfo?.isConnected ?: false
        }
    } ?: false