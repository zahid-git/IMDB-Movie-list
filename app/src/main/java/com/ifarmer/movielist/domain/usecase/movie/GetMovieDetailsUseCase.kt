package com.ifarmer.movielist.domain.usecase.movie

import androidx.paging.PagingData
import com.ifarmer.movielist.data.datasource.DataResult
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(movieId: Int): Flow<DataResult<MovieEntities>> {
        return repository.getMovieDetails(movieId)
    }
}