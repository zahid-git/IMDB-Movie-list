package com.ifarmer.movielist.data.repository

import com.ifarmer.movielist.data.datasource.DataResult
import com.ifarmer.movielist.data.datasource.local.database.movie.MovieLocalDataSource
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieWithWishlistEntities
import com.ifarmer.movielist.data.datasource.local.database.wishlist.WishListDataSource
import com.ifarmer.movielist.data.datasource.local.database.wishlist.entities.WishlistEntities
import com.ifarmer.movielist.domain.repository.WishListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class WishlistListRepositoryImpl @Inject constructor(
    private val wishListDataSource: WishListDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
): WishListRepository{

    override suspend fun saveWishlistData(movieId: Int): Flow<DataResult<WishlistEntities>> = flow{
        try {
            val movie = movieLocalDataSource.getSpecificMovie(movieId)
            movie?.let {
                wishListDataSource.addMovieFavorite(it.id)
                emit(DataResult.OnSuccess<WishlistEntities>(data = WishlistEntities(movieId = movieId)))
            } ?: run {
                emit(DataResult.OnFail<WishlistEntities>(data = null, message = "No movie found", code = 400))
            }
        } catch (e: Exception) { emit(DataResult.OnFail<WishlistEntities>(data = null, message = e.message, code = 400)) }
    }.flowOn(Dispatchers.IO)

    override suspend fun getTotalWishList(): Flow<DataResult<List<MovieEntities>>> = flow {
        try {
            val totalWishList = wishListDataSource.getWishList()
            totalWishList?.let { data->
                emit(DataResult.OnSuccess<List<MovieEntities>>(data = data))
            } ?: run {
                emit(DataResult.OnFail<List<MovieEntities>>(data = null, message = "No data", code = 400 ))
            }
        } catch (e: Exception) { emit(DataResult.OnFail<List<MovieEntities>>(data = null, message = e.message, code = 400 )) }
    }.flowOn(Dispatchers.IO)

}