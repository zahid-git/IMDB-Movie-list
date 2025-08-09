package com.ifarmer.movielist.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ifarmer.movielist.data.datasource.local.database.movie.MovieLocalDataSource
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieWithWishlistEntities

class MoviePagingSource(
    private val genre: String?,
    private val searchValue: String?,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val numOfOffScreenPage: Int = 1,
) : PagingSource<Int, MovieWithWishlistEntities>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieWithWishlistEntities> {
        val pageIndex = params.key ?: 1
        val page = params.loadSize
        return try {
            val movieEntities =
                movieLocalDataSource.getPaginatedData(searchValue = searchValue, genre = genre)

            LoadResult.Page(
                data = arrayListOf(),
                prevKey = if (pageIndex == 1) null else pageIndex - 1,
                nextKey = if (arrayListOf<MovieWithWishlistEntities>().isEmpty()) null else pageIndex + 1
            )
        } catch (e: Exception) {
            Log.e("ERROR", "load: " + e.message)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieWithWishlistEntities>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null

        return anchorPage.prevKey?.plus(numOfOffScreenPage)
            ?: anchorPage.nextKey?.minus(numOfOffScreenPage)
    }

}
