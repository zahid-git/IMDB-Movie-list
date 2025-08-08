package com.ifarmer.movielist.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ifarmer.movielist.data.datasource.DataResult
import com.ifarmer.movielist.domain.usecase.movie.GetAllMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val getAllMovies: GetAllMovieUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(SplashScreenViewState())
    val viewState = _viewState.asStateFlow()

    private val _viewEvent = MutableSharedFlow<SplashScreenViewEvent>()
    val viewEvent = _viewEvent.asSharedFlow()

    fun getDataAndStore() {
        viewModelScope.launch {
            _viewEvent.emit(SplashScreenViewEvent.FetchMovieData)
        }
    }

    fun fetchMovieData() {
        viewModelScope.launch {
            getAllMovies().collect { result ->
                when(result) {
                    is DataResult.OnLoading<*> -> {
                        _viewState.value = _viewState.value.copy(isLoading = true)
                    }
                    is DataResult.OnFail<*> -> {
                        _viewState.value = _viewState.value.copy(isLoading = false, errorMessage = result.message)
                    }
                    is DataResult.OnSuccess<*> -> {
                        _viewEvent.emit(SplashScreenViewEvent.GoToHomePage)
                    }
                }
            }
        }
    }

}