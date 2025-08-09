package com.ifarmer.movielist.data.datasource.local.database.movie

import androidx.paging.PagingSource
import com.ifarmer.movielist.data.datasource.local.database.movie.dao.MovieDao
import com.ifarmer.movielist.data.datasource.local.database.movie.dao.MovieGenreDao
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieGenresEntities
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieWithWishlistEntities
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

    fun getPaginatedData(genre: String?, searchValue: String?): PagingSource<Int, MovieWithWishlistEntities> {
        return movieDao.getMoviesWithOffset(
            search = searchValue,
            genre = genre
        )
    }

    suspend fun getSpecificMovie(movieId: Int): MovieEntities? {
        return movieDao.getMovieDetails(movieId = movieId)
    }

    fun saveAllMovieGenres(movieGenre: List<MovieGenresEntities>){
        return movieGenreDao.insetMovieGenres(movieGenre)
    }

    suspend fun getMovieGenres() : List<MovieGenresEntities>? {
        return movieGenreDao.getGenreList()
    }

}