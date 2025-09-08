package com.ifarmer.movielist.domain.usecase.movie

import com.ifarmer.movielist.data.datasource.DataResult
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieWithWishlistEntities
import com.ifarmer.movielist.data.model.response.MovieDataModel
import com.ifarmer.movielist.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(genre: String?, searchValue: String?): Flow<DataResult<List<MovieWithWishlistEntities>>> {
        return repository.fetchMovieList()
    }
}