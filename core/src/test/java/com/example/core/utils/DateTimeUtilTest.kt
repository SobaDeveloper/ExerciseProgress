package com.example.core.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.datetime.Instant
import org.junit.Assert.assertEquals
import org.junit.Test

class DateTimeUtilTest {

    @Test
    fun testFormatDurationUnderOneHour() {
        assertEquals("1 min", DateTimeUtil.formatDuration(59.0))
        assertEquals("30 min", DateTimeUtil.formatDuration(30 * 60.0))
    }

    @Test
    fun testFormatDurationOverOneHour() {
        assertEquals("1h 30m", DateTimeUtil.formatDuration(90 * 60.0))
        assertEquals("2h", DateTimeUtil.formatDuration(120 * 60.0))
    }

    @Test
    fun testFormatDate() {
        val instant = Instant.parse("2020-12-01T22:07:49Z")
        val expected = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            .format(Date(instant.toEpochMilliseconds()))
        assertEquals(expected, DateTimeUtil.formatDate(instant))
    }
}
