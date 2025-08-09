package com.ifarmer.movielist.data.model.response

import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities

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

fun MovieDataModel.toEntity(): MovieEntities {
    return MovieEntities(
        id = id,
        title = title,
        year = year,
        runtime = runtime,
        genres = genres,
        director = director,
        actors = actors,
        plot = plot,
        posterUrl = posterUrl
    )
}