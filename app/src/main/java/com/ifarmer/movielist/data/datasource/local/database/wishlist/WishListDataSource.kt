package com.ifarmer.movielist.data.datasource.local.database.wishlist

import androidx.paging.PagingSource
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieWithWishlistEntities
import com.ifarmer.movielist.data.datasource.local.database.wishlist.dao.WishListDao
import com.ifarmer.movielist.data.datasource.local.database.wishlist.entities.WishlistEntities
import javax.inject.Inject

class WishListDataSource @Inject constructor(
    private val wishlistDao: WishListDao,
) {

    fun addMovieFavorite(movieId: Int){
        return wishlistDao.addWishList(WishlistEntities(
            movieId = movieId
        ))
    }

    suspend fun getWishList(): List<MovieEntities>? {
        return wishlistDao.getWishlist()
    }

}