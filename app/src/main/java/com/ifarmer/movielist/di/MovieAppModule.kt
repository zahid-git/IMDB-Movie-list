package com.ifarmer.movielist.di

import com.ifarmer.movielist.data.datasource.local.database.movie.MovieLocalDataSource
import com.ifarmer.movielist.data.datasource.remote.ApiService
import com.ifarmer.movielist.data.repository.MovieRepositoryImpl
import com.ifarmer.movielist.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieAppModule {

    /**
     * Provides Movie Repository
     * */
    @Provides
    @Singleton
    fun provideMovieRepository(apiService: ApiService, movieLocalDataSource: MovieLocalDataSource): MovieRepository = MovieRepositoryImpl(apiService = apiService, movieLocalDataSource = movieLocalDataSource)

}