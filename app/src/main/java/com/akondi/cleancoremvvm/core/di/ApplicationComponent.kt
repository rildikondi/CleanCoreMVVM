package com.akondi.cleancoremvvm.core.di

import com.akondi.cleancoremvvm.AndroidApplication
import com.akondi.cleancoremvvm.core.di.viewmodel.ViewModelModule
import com.akondi.cleancoremvvm.core.navigation.RouteActivity
import com.akondi.cleancoremvvm.features.movies.MovieDetailsFragment
import com.akondi.cleancoremvvm.features.movies.MoviesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(routeActivity: RouteActivity)

    fun inject(moviesFragment: MoviesFragment)
    fun inject(movieDetailsFragment: MovieDetailsFragment)
}