package com.ifarmer.movielist.ui.screens.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ifarmer.movielist.data.datasource.DataResult
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieEntities
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieGenresEntities
import com.ifarmer.movielist.data.datasource.local.database.movie.entities.MovieWithWishlistEntities
import com.ifarmer.movielist.domain.usecase.movie.GetMovieGenreUserCase
import com.ifarmer.movielist.domain.usecase.movie.GetMoviePaginatedUseCase
import com.ifarmer.movielist.domain.usecase.wishlist.AddNewWishListUseCase
import com.ifarmer.movielist.domain.usecase.wishlist.GetWishListUseCase
import com.ifarmer.movielist.ui.screens.homepage.HomepageListAction.NavigateToDetail
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
    private val getMovieGenreUserCase: GetMovieGenreUserCase,
    private val addNewWishUseCase: AddNewWishListUseCase,
    private val getWishListUseCase: GetWishListUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(HomepageViewState())
    val viewState = _viewState.asStateFlow()

    private val _action = MutableSharedFlow<HomepageListAction>()
    val action = _action.asSharedFlow()

    init {
        getMovieData()
        getMovieGenreData()
        getWishList()
    }

    fun getMovieData() {
        _viewState.value = _viewState.value.copy(
            movieData = getMoviePaginatedUseCase(
                searchValue = viewState.value.searchValue,
                genre = viewState.value.selectedGenre
            ).cachedIn(viewModelScope),
            title = if (viewState.value.selectedGenre.isEmpty()) "All Movies" else "Search result for : " + viewState.value.selectedGenre
        )
    }

    fun getWishList() {
        viewModelScope.launch {
            getWishListUseCase().collect { result ->
                when (result) {
                    is DataResult.OnFail<*> -> TODO()
                    is DataResult.OnLoading<*> -> TODO()
                    is DataResult.OnSuccess<List<MovieEntities>> -> {
                        result.data?.size?.let { _viewState.value = _viewState.value.copy(wishCount = it) }
                    }
                }
            }
        }
    }

    fun getMovieGenreData() {
        viewModelScope.launch {
            getMovieGenreUserCase().collect {
                when (it) {
                    is DataResult.OnFail<*> -> {}
                    is DataResult.OnLoading<*> -> {}
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

    fun onEvent(event: HomepageViewEvent) {
        when (event) {
            is HomepageViewEvent.changeGenre -> {
                _viewState.value = _viewState.value.copy(
                    selectedGenre = event.genreName
                )
                getMovieData()
            }

            is HomepageViewEvent.goToMovieDetails -> {
                viewModelScope.launch {
                    _action.emit(NavigateToDetail(movieId = event.movieId))
                }
            }

            is HomepageViewEvent.changeListType -> {
                _viewState.value = _viewState.value.copy(
                    isGrid = event.isGrid
                )
            }

            is HomepageViewEvent.showGenre -> {
                _viewState.value = _viewState.value.copy(
                    isGenreShow = event.isExpand
                )
            }

            is HomepageViewEvent.changeSerachValue -> {
                _viewState.value = _viewState.value.copy(
                    searchValue = event.search
                )
                getMovieData()
            }

            is HomepageViewEvent.addToFavoriteItem -> {
                viewModelScope.launch {
                    addNewWishUseCase(movieId = event.movieId).collect { }
                    getWishList()
                }
            }
        }
    }

}