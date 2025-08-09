package com.ifarmer.movielist.domain.usecase.wishlist

import com.ifarmer.movielist.data.datasource.DataResult
import com.ifarmer.movielist.data.datasource.local.database.wishlist.entities.WishlistEntities
import com.ifarmer.movielist.domain.repository.WishListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddNewWishListUseCase @Inject constructor(
    private val wishListRepository: WishListRepository
) {
    suspend operator fun invoke(movieId: Int): Flow<DataResult<WishlistEntities>> {
        return wishListRepository.saveWishlistData(movieId)
    }

}