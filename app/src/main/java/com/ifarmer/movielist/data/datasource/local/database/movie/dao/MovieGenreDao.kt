package com.ifarmer.movielist.data.datasource.local.database.movie.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieGenresEntities

@Dao
interface MovieGenreDao {

    @Upsert
    fun insetMovieGenre(genre: MovieGenresEntities)

    @Upsert
    fun insetMovieGenres(genres: List<MovieGenresEntities>)

    @Query("SELECT * FROM genres")
    suspend fun getGenreList(): List<MovieGenresEntities>?




}