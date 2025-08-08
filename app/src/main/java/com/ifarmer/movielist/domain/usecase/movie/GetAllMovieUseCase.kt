package com.ifarmer.movielist.domain.usecase.movie

import com.ifarmer.movielist.data.datasource.DataResult
import com.ifarmer.movielist.data.model.response.MovieDataModel
import com.ifarmer.movielist.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetAllMovieUseCase (
    private val repository: MovieRepository
) {
    suspend operator fun invoke(): Flow<DataResult<List<MovieDataModel>>> {
        return repository.fetchMovieList()
    }
}