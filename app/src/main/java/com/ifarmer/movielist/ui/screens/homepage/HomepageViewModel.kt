package com.ifarmer.movielist.ui.screens.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ifarmer.movielist.data.datasource.DataResult
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieGenresEntities
import com.ifarmer.movielist.domain.usecase.movie.GetMovieGenreUserCase
import com.ifarmer.movielist.domain.usecase.movie.GetMoviePaginatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(
    private val getMoviePaginatedUseCase: GetMoviePaginatedUseCase,
    private val getMovieGenreUserCase: GetMovieGenreUserCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(HomepageViewState())
    val viewState = _viewState.asStateFlow()

    private val _action = MutableSharedFlow<HomepageListAction>()
    val action = _action.asSharedFlow()

    init {
        getMovieData("")
        getMovieGenreData()
    }

    fun getMovieData(genre: String?){
        viewModelScope.launch {
            _viewState.value = _viewState.value.copy(
                movieData = getMoviePaginatedUseCase(genre),
                title = if(genre.isNullOrEmpty()) "All Movies" else  "Search result for : $genre"
            )
        }
    }

    fun getMovieGenreData(){
        viewModelScope.launch {
            getMovieGenreUserCase().collect {
                when(it) {
                    is DataResult.OnFail<*> -> { }
                    is DataResult.OnLoading<*> -> { }
                    is DataResult.OnSuccess<List<MovieGenresEntities>> -> {
                        it.data?.let { data ->
                            _viewState.value = _viewState.value.copy(
                                movieGenres = data
                            )
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: HomepageViewEvent){
        when(event) {
            is HomepageViewEvent.changeGenre -> {
                getMovieData(event.genreName)
            }
            is HomepageViewEvent.goToMovieDetails -> {
                viewModelScope.launch {
                    _action.emit(HomepageListAction.NavigateToDetail(movieId = event.movieId))
                }
            }
        }
    }

}