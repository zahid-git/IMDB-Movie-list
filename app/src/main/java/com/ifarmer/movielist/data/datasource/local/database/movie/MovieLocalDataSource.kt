package com.ifarmer.movielist.data.datasource.local.database.movie

import com.ifarmer.movielist.data.datasource.local.database.movie.dao.MovieDao
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {

    fun saveAllMovies(movies: List<MovieEntities>){
        return movieDao.insetMovies(movies)
    }

    fun isDataExist(): Boolean {
        return movieDao.getMovies(1).isNotEmpty()
    }


}