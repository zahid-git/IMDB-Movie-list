package com.ifarmer.movielist.ui.screens.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.data.model.response.MovieDataModel
import com.ifarmer.movielist.domain.usecase.GetMoviePaginatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(
    getMoviePaginatedUseCase: GetMoviePaginatedUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(HomepageViewState())
    val viewState = _viewState.asStateFlow()

    private val _viewEvent = MutableSharedFlow<HomepageViewEvent>()
    val viewEvent = _viewEvent.asSharedFlow()

    var getMovieList = getMoviePaginatedUseCase().cachedIn(viewModelScope)

}