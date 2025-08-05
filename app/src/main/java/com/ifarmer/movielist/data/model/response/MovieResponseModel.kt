package com.ifarmer.movielist.data.model.response

data class MovieResponse(
    val genres: List<String>,
    val movies: List<MovieDataModel>
)

data class MovieDataModel(
    val id: Int,
    val title: String,
    val year: String,
    val runtime: String,
    val genres: List<String>,
    val director: String,
    val actors: String,
    val plot: String,
    val posterUrl: String
)