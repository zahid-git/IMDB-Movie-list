package com.ifarmer.movielist.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
/*

class ImagePagingSource(
    private val apiService: ApiService,
    private val numOfOffScreenPage: Int = 4,
) : PagingSource<Int, ImageListModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageListModel> {
        val pageIndex = params.key ?: 1
        val pageSize = params.loadSize
        return try {
            val responseData = apiService.fetchImages(pageIndex, pageSize)

            LoadResult.Page(
                data = responseData.body()!!,
                prevKey = if (pageIndex == 1) null else pageIndex - 1,
                nextKey = if (responseData.body()!!.isEmpty()) null else pageIndex + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageListModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null

        return anchorPage.prevKey?.plus(numOfOffScreenPage)
            ?: anchorPage.nextKey?.minus(numOfOffScreenPage)
    }

}*/
