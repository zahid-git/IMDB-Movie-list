package com.ifarmer.movielist.data.repository

import com.ifarmer.movielist.data.datasource.DataResult
import com.ifarmer.movielist.data.datasource.local.database.movie.MovieLocalDataSource
import com.ifarmer.movielist.data.datasource.local.database.movie.dao.MovieDao
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.data.datasource.remote.ApiService
import com.ifarmer.movielist.data.datasource.remote.NetworkCallback
import com.ifarmer.movielist.data.model.response.MovieDataModel
import com.ifarmer.movielist.data.model.response.toEntity
import com.ifarmer.movielist.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val movieDataSource: MovieLocalDataSource
) : MovieRepository, NetworkCallback() {

    override suspend fun fetchMovieList(): Flow<List<MovieDataModel>?> = flow{
        try {
            val movieData = safeAPICall { apiService.fetchMovieData() }

            when(movieData) {
                is DataResult.OnLoading<*> -> { }
                is DataResult.OnFail<*> -> {
                    emit(null)
                }
                is DataResult.OnSuccess<*> -> {
                    var movieList = movieData.data?.data?.movies.let {
                        var movieEntities: ArrayList<MovieEntities> = arrayListOf()
                        for (movie in it!!) {
                            movieEntities.add(movie.toEntity())
                        }
                        movieEntities
                    }
                    movieDataSource.saveAllMovies(movieList)
                }
            }
        } catch (e: Exception) {
            emit(throw Exception(e))
        }
    }.flowOn(Dispatchers.IO)

}