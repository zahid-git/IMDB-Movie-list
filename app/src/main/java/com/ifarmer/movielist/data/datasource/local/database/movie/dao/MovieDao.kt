package com.ifarmer.movielist.data.datasource.local.database.movie.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieWithWishlistEntities

@Dao
interface MovieDao {

    @Upsert
    fun insetMovie(movie: MovieEntities)

    @Upsert
    fun insetMovies(movies: List<MovieEntities>)

    @Delete
    fun deleteMovie(movie: MovieEntities)

    @Query("SELECT * FROM movies WHERE id = :movieId LIMIT 1")
    suspend fun getMovieDetails(movieId: Int): MovieEntities?

    @Query("SELECT movie.* , CASE WHEN wishlist.movie_id IS NOT NULL THEN 1 ELSE 0 END as isWishlistItem FROM movies movie LEFT JOIN wishlist wishlist ON movie.id = wishlist.movie_id  WHERE (title LIKE '%'|| :search ||'%' OR plot LIKE '%'|| :search ||'%') AND genres LIKE '%'|| :genre ||'%' ORDER BY movie.id DESC")
        fun getMoviesWithOffset(search: String? = "", genre: String? = ""): PagingSource<Int, MovieWithWishlistEntities>

    @Query("SELECT * FROM movies LIMIT :limit")
    fun getMovies(limit : Int ): List<MovieEntities>

    @Query("DELETE FROM movies")
    fun deleteAllMovies()

}