package com.ifarmer.movielist.domain.usecase.movie

import androidx.paging.PagingData
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviePaginatedUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(genre: String?, searchValue: String?): Flow<PagingData<MovieEntities>> {
        return repository.getPaginatedData(genre, searchValue)
    }
}