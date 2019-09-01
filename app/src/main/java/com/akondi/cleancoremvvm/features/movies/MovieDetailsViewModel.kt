package com.akondi.cleancoremvvm.features.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akondi.cleancoremvvm.core.platform.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel
@Inject constructor(private val getMovieDetails: GetMovieDetails,
                    private val playMovie: PlayMovie) : BaseViewModel() {

    var movieDetails: MutableLiveData<MovieDetailsView> = MutableLiveData()

    fun loadMovieDetails(movieId: Int) = viewModelScope.launch {
        getMovieDetails(GetMovieDetails.Params(movieId)) {
            it.either(
                ::handleFailure,
                ::handleMovieDetails
            )
        }
    }

    fun playMovie(url: String) = viewModelScope.launch {
        playMovie(PlayMovie.Params(url))
    }

    private fun handleMovieDetails(movie: MovieDetails) {
        this.movieDetails.value = MovieDetailsView(movie.id, movie.title, movie.poster,
            movie.summary, movie.cast, movie.director, movie.year, movie.trailer)
    }
}