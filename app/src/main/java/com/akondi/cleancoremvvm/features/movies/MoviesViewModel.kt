package com.akondi.cleancoremvvm.features.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akondi.cleancoremvvm.core.interactor.UseCase
import com.akondi.cleancoremvvm.core.platform.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel
@Inject constructor(private val getMovies: GetMovies) : BaseViewModel() {

    var movies: MutableLiveData<List<MovieView>> = MutableLiveData()

    fun loadMovies() = viewModelScope.launch {
        getMovies(UseCase.None()) { it.either(::handleFailure, ::handleMovieList) }
    }

    private fun handleMovieList(movies: List<Movie>) {
        this.movies.value = movies.map { MovieView(it.id, it.poster) }
    }

}