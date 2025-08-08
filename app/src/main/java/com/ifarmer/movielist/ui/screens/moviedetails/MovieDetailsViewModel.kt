package com.ifarmer.movielist.ui.screens.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ifarmer.movielist.data.datasource.DataResult
import com.ifarmer.movielist.domain.usecase.movie.GetAllMovieUseCase
import com.ifarmer.movielist.domain.usecase.movie.GetMovieDetailsUseCase
import com.ifarmer.movielist.ui.screens.homepage.HomepageViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(MovieDetailsViewState())
    val viewState = _viewState.asStateFlow()

    fun fetchMovieDetails(movieId: Int){
        viewModelScope.launch {
            movieDetailsUseCase.invoke(movieId = movieId).collect { details->
                when(details){
                    is DataResult.OnLoading<*> -> TODO()
                    is DataResult.OnFail<*> -> TODO()
                    is DataResult.OnSuccess<*> -> {
                        val movieData = details.data
                        _viewState.value = _viewState.value.copy(
                            movieName = movieData?.title ?: "",
                            releaseYear = movieData?.year ?: "",
                            movieDuration = movieData?.runtime +" Min",
                            directorName = movieData?.director ?: "",
                            genres = movieData?.genres ?: arrayListOf(),
                            image = movieData?.posterUrl ?: "",
                            overview = movieData?.plot ?: "",
                            actors = movieData?.actors?.split(",") ?: arrayListOf(),
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: MovieDetailsViewEvent) {
        when(event) {
            is MovieDetailsViewEvent.fetchMovieData -> {
                fetchMovieDetails(event.movieId)
            }
        }
    }



}