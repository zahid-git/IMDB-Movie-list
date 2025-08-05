package com.ifarmer.movielist.data.datasource.remote

import com.ifarmer.movielist.data.model.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("db.json")
    suspend fun fetchMovieData(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ) : Response<List<MovieResponse>>

}