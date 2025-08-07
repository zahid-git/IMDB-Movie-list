package com.ifarmer.movielist.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ifarmer.movielist.data.datasource.DataResult
import com.ifarmer.movielist.data.datasource.MoviePagingSource
import com.ifarmer.movielist.data.datasource.local.database.movie.MovieLocalDataSource
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
    private val movieLocalDataSource: MovieLocalDataSource
) : MovieRepository, NetworkCallback() {

    override suspend fun fetchMovieList(): Flow<DataResult<List<MovieDataModel>>> = flow{
        try {
            emit(DataResult.OnLoading<List<MovieDataModel>>())
            if (movieLocalDataSource.isDataExist()) {
                emit(DataResult.OnSuccess<List<MovieDataModel>>(data = arrayListOf()))
                return@flow
            }
            val movieData = safeAPICall { apiService.fetchMovieData() }

            when(movieData) {
                is DataResult.OnLoading<*> -> { }
                is DataResult.OnFail<*> -> {
                    emit(DataResult.OnFail<List<MovieDataModel>>(data = null, code = movieData.code, message = movieData.message))
                }
                is DataResult.OnSuccess<*> -> {
                    var movieList = movieData.data?.data?.movies.let {
                        var movieEntities: ArrayList<MovieEntities> = arrayListOf()
                        for (movie in it!!) {
                            movieEntities.add(movie.toEntity())
                        }
                        movieEntities
                    }
                    movieLocalDataSource.saveAllMovies(movieList)
                    emit(DataResult.OnSuccess<List<MovieDataModel>>(data = arrayListOf()))
                }
            }
        } catch (e: Exception) {
            emit(DataResult.OnFail<List<MovieDataModel>>(data = null, code = null, message = e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun getPaginatedData(): Flow<PagingData<MovieEntities>> {
        val pageSize: Int = 10 // As per business logic
        val enablePlaceHolders: Boolean = true
        val prefetchDistance: Int = 1
        val initialLoadSize: Int = 7
        val maxCacheSize: Int = 2000
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = enablePlaceHolders,
                prefetchDistance = prefetchDistance,
                initialLoadSize = initialLoadSize,
                maxSize = maxCacheSize
            ), pagingSourceFactory = {
                MoviePagingSource(movieLocalDataSource, 1)
            }
        ).flow
    }

}