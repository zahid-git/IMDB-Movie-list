package com.ifarmer.movielist.domain.repository

import androidx.paging.PagingData
import com.ifarmer.movielist.data.datasource.DataResult
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.data.model.response.MovieDataModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun fetchMovieList(): Flow<DataResult<List<MovieDataModel>>>
    fun getPaginatedData(): Flow<PagingData<MovieEntities>>

}