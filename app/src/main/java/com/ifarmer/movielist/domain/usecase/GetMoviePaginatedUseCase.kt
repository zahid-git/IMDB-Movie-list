package com.ifarmer.movielist.domain.usecase

import androidx.paging.PagingData
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.data.model.response.MovieDataModel
import com.ifarmer.movielist.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMoviePaginatedUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<PagingData<MovieEntities>> {
        return repository.getPaginatedData()
    }
}