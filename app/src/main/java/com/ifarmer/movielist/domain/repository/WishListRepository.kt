package com.ifarmer.movielist.domain.repository

import com.ifarmer.movielist.data.datasource.DataResult
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.data.datasource.local.database.wishlist.entities.WishlistEntities
import kotlinx.coroutines.flow.Flow

interface WishListRepository {

    suspend fun saveWishlistData(movieId: Int): Flow<DataResult<WishlistEntities>>
    suspend fun getTotalWishList(): Flow<DataResult<List<MovieEntities>>>

}