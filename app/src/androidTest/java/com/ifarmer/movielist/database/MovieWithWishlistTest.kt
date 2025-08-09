package com.ifarmer.movielist.database

import android.util.Log
import androidx.paging.PagingSource
import com.google.gson.Gson
import com.ifarmer.movielist.data.datasource.local.database.AppDatabase
import com.ifarmer.movielist.data.datasource.local.database.movie.dao.MovieDao
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieWithWishlistEntities
import com.ifarmer.movielist.data.datasource.local.database.wishlist.dao.WishListDao
import com.ifarmer.movielist.data.datasource.local.database.wishlist.entities.WishlistEntities
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieWithWishlistTest {
    private lateinit var database: AppDatabase
    private lateinit var movieDao: MovieDao
    private lateinit var wishlistDao: WishListDao

    @Before
    fun setup() {
        database = FakeDatabaseProvider.createInMemoryDatabase()
        movieDao = database.movieDao
        wishlistDao = database.wishlistDao

        runBlocking {
            for (i in 1..100) {
                val movie = MovieEntities(
                    id = i,
                    title = "Fake Movie $i",
                    year = "2024",
                    runtime = "120",
                    genres = arrayListOf("Drama", "Horror"),
                    director = "John Doe",
                    actors = "Actor 1, Actor 2",
                    plot = "Fake Plot",
                    posterUrl = "https://www.google.image.png"
                )
                movieDao.insetMovie(movie)
            }

            wishlistDao.addWishList(WishlistEntities(movieId = 100))
            wishlistDao.addWishList(WishlistEntities(movieId = 94))
            wishlistDao.addWishList(WishlistEntities(movieId = 92))
        }
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun testInsertAndGet() = runBlocking {

        val pagingSource: PagingSource<Int, MovieWithWishlistEntities> = movieDao.getMoviesWithOffset()

        val loadResult = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 10,
                placeholdersEnabled = false
            )
        )



        when (loadResult) {
            is PagingSource.LoadResult.Error<*, *> -> {
                Assert.fail("Load result is not a Page")
            }
            is PagingSource.LoadResult.Invalid<*, *> -> {
                Assert.fail("Load result is not a Page")
            }
            is PagingSource.LoadResult.Page<Int, MovieWithWishlistEntities> -> {
                Log.e("TAG", "testInsertAndGet: "+ Gson().toJson(loadResult.data) )

                val data = loadResult.data
                Assert.assertEquals(10, data.size)

                // Movie 1 should have wishlist
                val movie1 = data[0]
                Assert.assertEquals(100, movie1.movie.id)
                Assert.assertTrue(movie1.isWishlistItem)

                // Movie 2 should NOT have wishlist (null)
                val movie2 = data[1]
                Assert.assertEquals(99, movie2.movie.id)
                Assert.assertFalse(movie2.isWishlistItem)

                // Movie 3 should have wishlist
                val movie4 = data[6]
                Assert.assertEquals(94, movie4.movie.id)
                Assert.assertTrue(movie4.isWishlistItem)

                // Check nextKey is 5 (since we loaded 5 items)
                Assert.assertEquals(10, loadResult.nextKey)
            }
        }
    }
}