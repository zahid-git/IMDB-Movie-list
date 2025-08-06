package com.ifarmer.movielist.data.datasource.local.database.movie.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntities(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "runtime") val runtime: String,
    @ColumnInfo(name = "genres") val genres: List<String>,
    @ColumnInfo(name = "director") val director: String,
    @ColumnInfo(name = "actors") val actors: String,
    @ColumnInfo(name = "plot") val plot: String,
    @ColumnInfo(name = "posterUrl") val posterUrl: String,
)