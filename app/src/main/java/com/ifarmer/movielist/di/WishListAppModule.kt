package com.ifarmer.movielist.di

import com.ifarmer.movielist.data.datasource.local.database.movie.MovieLocalDataSource
import com.ifarmer.movielist.data.datasource.local.database.wishlist.WishListDataSource
import com.ifarmer.movielist.data.repository.WishlistListRepositoryImpl
import com.ifarmer.movielist.domain.repository.WishListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WishListAppModule {

    /**
     * Provides WishList Repository
     * */
    @Provides
    @Singleton
    fun provideWishListRepository(
        wishListDataSource: WishListDataSource,
        movieLocalDataSource: MovieLocalDataSource
    ): WishListRepository =
        WishlistListRepositoryImpl(
            wishListDataSource = wishListDataSource,
            movieLocalDataSource = movieLocalDataSource
        )
}