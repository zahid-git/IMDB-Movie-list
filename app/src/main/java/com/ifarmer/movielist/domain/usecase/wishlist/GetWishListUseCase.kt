package com.ifarmer.movielist.domain.usecase.wishlist

import com.ifarmer.movielist.data.datasource.DataResult
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.domain.repository.WishListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWishListUseCase @Inject constructor(
    private val wishListRepository: WishListRepository
) {
    suspend operator fun invoke(): Flow<DataResult<List<MovieEntities>>> {
        return wishListRepository.getTotalWishList()
    }
}