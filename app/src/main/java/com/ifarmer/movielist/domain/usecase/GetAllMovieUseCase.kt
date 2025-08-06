package com.ifarmer.movielist.domain.usecase

import com.ifarmer.movielist.domain.repository.MovieRepository

class GetAllMovieUseCase (
    private val repository: MovieRepository
) {
    suspend operator fun invoke(){
        return repository.fetchMovieList()
    }
}