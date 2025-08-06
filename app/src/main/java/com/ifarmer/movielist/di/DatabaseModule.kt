package com.ifarmer.movielist.di

import android.content.Context
import androidx.room.Room
import com.ifarmer.movielist.data.datasource.local.database.AppDatabase
import com.ifarmer.movielist.data.datasource.local.database.movie.MovieLocalDataSource
import com.ifarmer.movielist.data.datasource.local.database.movie.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides Database
     * */
    @Singleton
    @Provides
    fun provideLocalDatabase(context: Context): AppDatabase { return Room.databaseBuilder(context, AppDatabase::class.java, "MovieDB").build() }

    /**
     * Provides MovieDao
     * */
    @Singleton
    @Provides
    fun provideMovieDAO(db: AppDatabase): MovieDao = db.movieDao

    /**
     * Provides MovieDatasource
     * */
    @Singleton
    @Provides
    fun provideMovieDataSource(dao: MovieDao): MovieLocalDataSource = MovieLocalDataSource(dao)

}