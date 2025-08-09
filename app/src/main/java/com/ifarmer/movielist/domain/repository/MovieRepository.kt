package com.ifarmer.movielist.domain.repository

import androidx.paging.PagingData
import com.ifarmer.movielist.data.datasource.DataResult
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieGenresEntities
import com.ifarmer.movielist.data.model.response.MovieDataModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun fetchMovieList(): Flow<DataResult<List<MovieDataModel>>>
    fun getPaginatedData(genre: String?, searchValue: String?): Flow<PagingData<MovieEntities>>
    suspend fun getMovieGenres(): Flow<DataResult<List<MovieGenresEntities>>>
    fun getMovieDetails(movieId: Int): Flow<DataResult<MovieEntities>>

}