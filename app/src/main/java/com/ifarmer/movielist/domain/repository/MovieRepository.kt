package com.ifarmer.movielist.domain.repository

import com.ifarmer.movielist.data.datasource.DataResult
import com.ifarmer.movielist.data.datasource.model.BaseDataModel
import com.ifarmer.movielist.data.model.response.MovieDataModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun fetchMovieList(): Flow<List<MovieDataModel>?>

}