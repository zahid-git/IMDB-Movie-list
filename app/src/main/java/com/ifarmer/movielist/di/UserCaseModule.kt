package com.ifarmer.movielist.di

import com.google.gson.Gson
import com.ifarmer.movielist.BuildConfig
import com.ifarmer.movielist.data.datasource.remote.ApiService
import com.ifarmer.movielist.domain.usecase.MovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserCaseModule {

    /**
     * Provides GSON
     * */
    @Provides
    @Singleton
    fun provideMovieUseCase(): MovieUseCase {
        return MovieUseCase()
    }


}