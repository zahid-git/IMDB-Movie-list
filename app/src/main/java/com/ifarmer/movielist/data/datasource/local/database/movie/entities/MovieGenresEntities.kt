package com.ifarmer.movielist.data.datasource.local.database.movie.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class MovieGenresEntities(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String
)