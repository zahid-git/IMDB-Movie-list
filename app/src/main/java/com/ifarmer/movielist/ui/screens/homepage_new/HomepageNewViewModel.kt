package com.ifarmer.movielist.ui.screens.homepage_new

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ifarmer.movielist.data.datasource.DataResult
import com.ifarmer.movielist.domain.usecase.movie.GetMovieListUseCase
import com.ifarmer.movielist.ui.screens.homepage.HomepageListAction.NavigateToDetail
import com.ifarmer.movielist.ui.screens.homepage.HomepageViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomepageNewViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
): ViewModel() {

    private val _viewState = MutableStateFlow(HomepageNewViewState())
    val viewState = _viewState.asStateFlow()

    private val _viewEvent = MutableSharedFlow<HomepageNewViewEvent>()
    val viewEvent = _viewEvent.asSharedFlow()

    init {
        getMovieList()
    }

    fun getMovieList(){
        viewModelScope.launch {
            getMovieListUseCase.invoke("","").collect{
                when(it){
                    is DataResult.OnFail<*> -> { }
                    is DataResult.OnLoading<*> -> { }
                    is DataResult.OnSuccess<*> -> {
                        it.data?.let { data ->
                            _viewState.value = _viewState.value.copy(
                                movieList = data
                            )
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: HomepageNewViewEvent) {
        when (event) {
            is HomepageNewViewEvent.OnFavorite -> {
                val updatedList = _viewState.value.movieList.toMutableList()
                updatedList[event.index] = updatedList[event.index].copy(isWishlistItem = true)
                _viewState.value = _viewState.value.copy(
                    movieList = updatedList
                )
            }
        }
    }

}