package com.akondi.cleancoremvvm.features.movies

import com.akondi.cleancoremvvm.core.exception.Failure

class MovieFailure {
    class ListNotAvailable: Failure.FeatureFailure()
    class NonExistentMovie: Failure.FeatureFailure()
}