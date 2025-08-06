package com.ifarmer.movielist.data.datasource.local.database.movie.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities

@Dao
interface MovieDao {

    @Upsert
    fun insetMovie(movie: MovieEntities)

    @Upsert
    fun insetMovies(movies: List<MovieEntities>)

    @Delete
    fun deleteMovie(movie: MovieEntities)

    @Query("SELECT * FROM movies")
    fun getUserProfileList(): List<MovieEntities>

    @Query("SELECT * FROM movies ORDER BY id DESC LIMIT :limit OFFSET :offset")
    fun getMoviesWithOffset(limit: Int, offset: Int): List<MovieEntities>

    @Query("SELECT * FROM movies LIMIT :limit")
    fun getMovies(limit : Int ): List<MovieEntities>

    @Query("DELETE FROM movies")
    fun deleteAllMovies()

}