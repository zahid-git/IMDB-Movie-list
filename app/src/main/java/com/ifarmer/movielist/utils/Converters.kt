package com.ifarmer.movielist.utils

import androidx.room.TypeConverter
import java.util.Date

class Converters {

    @TypeConverter
    fun fromGenreList(genres: List<String>): String {
        return genres.joinToString(separator = ",")
    }

    @TypeConverter
    fun toGenreList(data: String): List<String> {
        return if (data.isEmpty()) emptyList() else data.split(",")
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

}