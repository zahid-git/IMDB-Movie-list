package com.ifarmer.movielist.ui.screens.moviedetails

data class MovieDetailsViewState(
    var movieName: String = "",
    var releaseYear: String = "",
    var movieDuration: String = "",
    var directorName: String = "",
    var genres: List<String> = arrayListOf(),
    var actors: List<String> = arrayListOf(),
    var image: String = "",
    var overview: String = "",
)
