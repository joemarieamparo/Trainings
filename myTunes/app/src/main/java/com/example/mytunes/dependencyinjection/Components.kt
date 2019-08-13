package com.example.mytunes.dependencyinjection.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.mytunes.api.DataManager
import com.example.mytunes.app.MyTunesApplication
import com.example.mytunes.dependencyinjection.PerActivity
import com.example.mytunes.dependencyinjection.PerDataManager
import com.example.mytunes.mvp.main.MainActivity
import com.example.mytunes.mvp.main.MainPresenter
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * ApplicationComponent is the component used in dependency injection.
 * The declaration here is seen through its usage and/or the entire application
 */
@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {

    fun inject(application: MyTunesApplication)

    fun getDataManager(): DataManager

    fun getRetrofit(): Retrofit

    fun getContext(): Context

    fun getSharedPreferences(): SharedPreferences
}

/**
 * DataManagerComponent is the component mainly use for DataManager class
 */
@PerDataManager
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ApiServicesModule::class, LocalRepositoryModule::class]
)
interface DataManagerComponent {

    fun inject(manager: DataManager)
}

/**
 * ActivityComponent is use in the android activity only.
 */
@PerActivity
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [PresenterModule::class]
)
interface ActivityComponent {
    fun inject(activity: MainActivity)
    fun inject(presenter: MainPresenter)
}
