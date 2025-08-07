package com.ifarmer.movielist.domain.usecase

import com.ifarmer.movielist.data.datasource.DataResult
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieGenresEntities
import com.ifarmer.movielist.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieGenreUserCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(): Flow<DataResult<List<MovieGenresEntities>>> {
        return repository.getMovieGenres()
    }
}