package com.example.core.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt
import kotlinx.datetime.Instant

object DateTimeUtil {

    fun formatDuration(seconds: Double): String {
        val totalMinutes = (seconds / 60).roundToInt()
        return if (totalMinutes >= 60) {
            val hours = totalMinutes / 60
            val remainingMinutes = totalMinutes % 60
            if (remainingMinutes > 0) "${hours}h ${remainingMinutes}m" else "${hours}h"
        } else {
            "$totalMinutes min"
        }
    }

    fun formatDate(instant: Instant): String {
        val date = Date(instant.toEpochMilliseconds())
        val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        return formatter.format(date)
    }

    fun formatTime(instant: Instant): String {
        val date = Date(instant.toEpochMilliseconds())
        val formatter = SimpleDateFormat("h:mm a", Locale.getDefault())
        return formatter.format(date)
    }
}