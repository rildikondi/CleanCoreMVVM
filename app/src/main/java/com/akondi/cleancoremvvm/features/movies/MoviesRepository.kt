package com.akondi.cleancoremvvm.features.movies

import com.akondi.cleancoremvvm.core.exception.Failure
import com.akondi.cleancoremvvm.core.extension.ConnectivityMode
import com.akondi.cleancoremvvm.core.functional.Either
import com.akondi.cleancoremvvm.core.platform.NetworkHandler
import retrofit2.Call
import javax.inject.Inject

interface MoviesRepository {
    fun movies(): Either<Failure, List<Movie>>
    fun movieDetails(movieId: Int): Either<Failure, MovieDetails>

    class Network
    @Inject constructor(private val networkHandler: NetworkHandler,
                        private val service: MoviesService) : MoviesRepository {

        override fun movies(): Either<Failure, List<Movie>> {
            return when (networkHandler.isConnected) {
                ConnectivityMode.NONE -> Either.Left(Failure.NetworkConnection)
                else -> request(service.movies(), { it.map { it.toMovie() } }, emptyList())
            }
        }

        override fun movieDetails(movieId: Int): Either<Failure, MovieDetails> {
            return when (networkHandler.isConnected) {
                ConnectivityMode.NONE -> Either.Left(Failure.NetworkConnection)
                else -> request(service.movieDetails(movieId), { it.toMovieDetails() }, MovieDetailsEntity.empty())
            }
        }

        private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Either.Right(transform((response.body() ?: default)))
                    false -> Either.Left(Failure.ServerError)
                }
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError)
            }
        }
    }
}