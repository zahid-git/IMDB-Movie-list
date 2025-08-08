package com.ifarmer.movielist.ui.screens.moviedetails

import androidx.lifecycle.ViewModel
import com.ifarmer.movielist.domain.usecase.GetAllMovieUseCase
import com.ifarmer.movielist.ui.screens.homepage.HomepageListAction
import com.ifarmer.movielist.ui.screens.homepage.HomepageViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieUserCase: GetAllMovieUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(MovieDetailsViewState())
    val viewState = _viewState.asStateFlow()



}