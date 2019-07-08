package com.ntxdroid.spacex.core.platform

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

// See https://developer.android.com/training/data-storage/room/referencing-data
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?) = timeStampToDate(value)

    @TypeConverter
    fun dateToTimestamp(date: Date?) = dateToLong(date)

    companion object {
        const val DATE_VIEW_FORMAT = "EEE, MMM d, yyyy HH:mm z"

        fun timeStampToDate(value: Long?) = value?.let { Date(it) }
        fun dateToLong(date: Date?) = date?.time

        fun dateString(time: Long, format: String): String {
            val dateFormat = SimpleDateFormat(format, Locale.getDefault())
            val dateTime = Date(time*1000)
            return dateFormat.format(dateTime)
        }
    }
}
