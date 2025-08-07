package com.ifarmer.movielist.data.datasource.local.database.movie

import com.ifarmer.movielist.data.datasource.local.database.movie.dao.MovieDao
import com.ifarmer.movielist.data.datasource.local.database.movie.dao.MovieGenreDao
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieGenresEntities
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao,
    private val movieGenreDao: MovieGenreDao
) {

    fun saveAllMovies(movies: List<MovieEntities>) {
        return movieDao.insetMovies(movies)
    }

    fun isDataExist(): Boolean {
        return movieDao.getMovies(1).isNotEmpty()
    }

    suspend fun getPaginatedData(genre: String?, limit: Int, page: Int): List<MovieEntities> {
        return movieDao.getMoviesWithOffset(
            genre = genre,
            limit = limit,
            offset = page*limit
        )
    }

    fun saveAllMovieGenres(movieGenre: List<MovieGenresEntities>){
        return movieGenreDao.insetMovieGenres(movieGenre)
    }

    suspend fun getMovieGenres() : List<MovieGenresEntities>? {
        return movieGenreDao.getGenreList()
    }
}