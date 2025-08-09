package com.ifarmer.movielist.data.datasource.local.database.wishlist.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(tableName = "wishlist", indices = [Index(value = ["movie_id"], unique = true)])
data class WishlistEntities(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "created_at", defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: String = Calendar.getInstance().time.toString()
)