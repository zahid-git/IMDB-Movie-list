package com.ifarmer.movielist.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ifarmer.movielist.data.datasource.local.database.movie.dao.MovieDao
import com.ifarmer.movielist.data.datasource.local.database.movie.dao.MovieGenreDao
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieGenresEntities
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieWithWishlistEntities
import com.ifarmer.movielist.data.datasource.local.database.wishlist.dao.WishListDao
import com.ifarmer.movielist.data.datasource.local.database.wishlist.entities.WishlistEntities
import com.ifarmer.movielist.utils.Converters

@Database(
    entities = [MovieEntities::class, MovieGenresEntities::class, WishlistEntities::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val movieGenreDao: MovieGenreDao
    abstract val wishlistDao: WishListDao
}