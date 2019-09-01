package com.akondi.cleancoremvvm.features.movies

import com.akondi.cleancoremvvm.core.interactor.UseCase
import com.akondi.cleancoremvvm.core.interactor.UseCase.None
import javax.inject.Inject

class GetMovies
@Inject constructor(private val moviesRepository: MoviesRepository) : UseCase<List<Movie>, None>() {

    override suspend fun run(params: None) = moviesRepository.movies()
}

