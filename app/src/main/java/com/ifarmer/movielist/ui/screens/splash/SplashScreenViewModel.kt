package com.ifarmer.movielist.ui.screens.splash

import androidx.lifecycle.ViewModel
import com.ifarmer.movielist.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    movieUseCase: MovieUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(SplashScreenViewState())
    val viewState = _viewState.asStateFlow()

    private val _viewEvent = MutableSharedFlow<SplashScreenViewEvent>()
    val viewEvent = _viewEvent.asSharedFlow()


}