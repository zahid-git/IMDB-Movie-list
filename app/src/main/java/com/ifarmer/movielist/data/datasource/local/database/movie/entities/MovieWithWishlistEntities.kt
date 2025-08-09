package com.ifarmer.movielist.data.datasource.local.database.movie.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class MovieWithWishlistEntities(
    @Embedded var movie: MovieEntities,
    @ColumnInfo(name = "isWishlistItem") var isWishlistItem: Boolean
)