package com.ifarmer.movielist.data.datasource.local.database.wishlist.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieWithWishlistEntities
import com.ifarmer.movielist.data.datasource.local.database.wishlist.entities.WishlistEntities

@Dao
interface WishListDao {

    @Upsert
    fun addWishList(wishList: WishlistEntities)

    @Query("SELECT movie.* FROM movies movie LEFT JOIN wishlist wishlist ON movie.id = wishlist.movie_id  WHERE wishlist.movie_id IS NOT NULL ORDER BY movie.id DESC")
    suspend fun getWishlist(): List<MovieEntities>?

}