package com.akondi.cleancoremvvm

import android.app.Application
import com.akondi.cleancoremvvm.core.di.ApplicationComponent
import com.akondi.cleancoremvvm.core.di.ApplicationModule
import com.akondi.cleancoremvvm.core.di.DaggerApplicationComponent
import leakcanary.AppWatcher

class AndroidApplication : Application() {
    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
        this.initializeLeakDetection()
    }

    private fun injectMembers() = appComponent.inject(this)

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) AppWatcher.config.copy(watchFragmentViews = false)
    }
}