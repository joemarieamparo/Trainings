package com.example.mytunes.app

import android.app.Application
import com.example.mytunes.dependencyinjection.modules.*

class MyTunesApplication : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent
            .builder()
            .applicationComponent(applicationComponent)
            .presenterModule(PresenterModule(this))
            .build()
    }


    override fun onCreate() {
        super.onCreate()
        applicationComponent.inject(this)
    }

}