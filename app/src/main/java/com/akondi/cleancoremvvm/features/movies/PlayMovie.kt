package com.akondi.cleancoremvvm.features.movies

import android.content.Context
import com.akondi.cleancoremvvm.core.exception.Failure
import com.akondi.cleancoremvvm.core.functional.Either
import com.akondi.cleancoremvvm.core.functional.Either.Right
import com.akondi.cleancoremvvm.core.interactor.UseCase
import com.akondi.cleancoremvvm.core.navigation.Navigator
import com.akondi.cleancoremvvm.features.movies.PlayMovie.Params
import javax.inject.Inject

class PlayMovie
@Inject constructor(private val context: Context,
                    private val navigator: Navigator
) : UseCase<UseCase.None, Params>() {

    override suspend fun run(params: Params): Either<Failure, None> {
        navigator.openVideo(context, params.url)
        return Right(None())
    }

    data class Params(val url: String)
}