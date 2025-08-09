package com.ifarmer.movielist.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ifarmer.movielist.data.datasource.DataResult
import com.ifarmer.movielist.data.datasource.MoviePagingSource
import com.ifarmer.movielist.data.datasource.local.database.movie.MovieLocalDataSource
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieGenresEntities
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieWithWishlistEntities
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

    override suspend fun fetchMovieList(): Flow<DataResult<List<MovieDataModel>>> =
        flow {
            try {
                emit(DataResult.OnLoading<List<MovieDataModel>>())
                if (movieLocalDataSource.isDataExist()) {
                    emit(DataResult.OnSuccess<List<MovieDataModel>>(data = arrayListOf()))
                    return@flow
                }
                val movieData = safeAPICall { apiService.fetchMovieData() }

                when (movieData) {
                    is DataResult.OnLoading<*> -> {}
                    is DataResult.OnFail<*> -> {
                        emit(
                            DataResult.OnFail<List<MovieDataModel>>(
                                data = null,
                                code = movieData.code,
                                message = movieData.message
                            )
                        )
                    }

                    is DataResult.OnSuccess<*> -> {
                        val movieList = ArrayList(movieData.data?.data?.movies?.map { it.toEntity() } ?: emptyList())
                        movieLocalDataSource.saveAllMovies(movieList)

                        var movieGenreData =  movieData.data?.data?.genres
                            ?.map { MovieGenresEntities(name = it) }
                            ?.let { ArrayList(it) } ?: arrayListOf()
                        movieLocalDataSource.saveAllMovieGenres(movieGenreData)


                        emit(DataResult.OnSuccess<List<MovieDataModel>>(data = arrayListOf()))
                    }
                }
            } catch (e: Exception) {
                emit(
                    DataResult.OnFail<List<MovieDataModel>>(
                        data = null,
                        code = null,
                        message = e.message
                    )
                )
            }
        }.flowOn(Dispatchers.IO)

    override fun getPaginatedData(genre: String?, searchValue: String?): Flow<PagingData<MovieWithWishlistEntities>> {
        val pageSize: Int = 10 // As per business logic
        val enablePlaceHolders = true
        val prefetchDistance = 3
        val initialLoadSize = 10
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = enablePlaceHolders,
                prefetchDistance = prefetchDistance,
                initialLoadSize = initialLoadSize,
            ), pagingSourceFactory = {
                movieLocalDataSource.getPaginatedData(searchValue = searchValue, genre = genre)
            }
        ).flow
    }

    override suspend fun getMovieGenres(): Flow<DataResult<List<MovieGenresEntities>>> = flow {
        try {
            emit(DataResult.OnLoading<List<MovieGenresEntities>>())
            var movieGenres = movieLocalDataSource.getMovieGenres()
            emit(DataResult.OnSuccess<List<MovieGenresEntities>>(data = movieGenres))
        } catch (e: Exception) {
            emit(
                DataResult.OnFail<List<MovieGenresEntities>>(
                    data = null,
                    code = 400,
                    message = e.message
                )
            )
        }
    }.flowOn(Dispatchers.IO)

    override fun getMovieDetails(movieId: Int): Flow<DataResult<MovieEntities>> = flow {
        try {
            val movieDetails: MovieEntities? = movieLocalDataSource.getSpecificMovie(movieId = movieId)
            movieDetails.let {
                emit(DataResult.OnSuccess<MovieEntities>(
                    data = it
                ))
            } ?: run {
                emit(
                    DataResult.OnFail<MovieEntities>(
                        code = 400, message = "No Movie found", data = null
                    )
                )
            }
        } catch (e: Exception) {
            emit(
                DataResult.OnFail<MovieEntities>(
                    code = 400, message = e.message, data = null
                )
            )
        }
    }.flowOn(Dispatchers.IO)


}