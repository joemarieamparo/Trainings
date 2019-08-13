package com.example.mytunes.dependencyinjection.modules

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.example.mytunes.BuildConfig
import com.example.mytunes.api.ApiServices
import com.example.mytunes.api.DataManager
import com.example.mytunes.api.room.LocalRepository
import com.example.mytunes.api.room.MoviesRoomDatabase
import com.example.mytunes.app.MyTunesApplication
import com.example.mytunes.dependencyinjection.PerActivity
import com.example.mytunes.dependencyinjection.PerDataManager
import com.example.mytunes.mvp.main.MainPresenter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApplicationModule(val application: MyTunesApplication) {

    @Provides
    @Singleton
    internal fun providesMyTunesApplication(): MyTunesApplication {
        return application
    }

    @Provides
    @Singleton
    internal fun providesContext(application: MyTunesApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    internal fun providesDataManager(context: Context): DataManager {
        return DataManager(context)
    }

    @Provides
    @Singleton
    internal fun providesSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideOkHttpCache(context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(context.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.cache(cache)
        return client.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BuildConfig.SERVER_URL)
            .client(okHttpClient)
            .build()
    }
}

@Module
class PresenterModule (var context: Context) {

    @Provides
    @PerActivity
    internal fun provideMainPresenter() : MainPresenter {
        return MainPresenter(context)
    }
}

@Module
class ApiServicesModule {

    @Provides
    @PerDataManager
    internal fun provideApiServices(retrofit: Retrofit) : ApiServices {
        return retrofit.create(ApiServices::class.java)
    }
}

@Module
class LocalRepositoryModule {

    @Provides
    @PerDataManager
    internal fun provideMoviesRoomDatabase(context: Context): MoviesRoomDatabase {
        return MoviesRoomDatabase.getDatabase(context)!!
    }

    @Provides
    @PerDataManager
    internal fun provideLocalRepository(database: MoviesRoomDatabase) : LocalRepository {
        return LocalRepository(database)
    }
}

@GlideModule
class ITuneGlideModule : AppGlideModule()