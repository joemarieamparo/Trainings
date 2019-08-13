package com.example.mytunes.api

import android.content.Context
import android.content.SharedPreferences
import com.example.mytunes.api.models.Movie
import com.example.mytunes.api.room.LocalRepository
import com.example.mytunes.dependencyinjection.modules.DaggerDataManagerComponent
import com.example.mytunes.utils.applicationComponent
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * DataManager handles all data request in the application.
 * This class must only have access to ApiServices and/or LocalRepository
 */
class DataManager (var context: Context) {

    @Inject
    lateinit var apiServices: ApiServices

    @Inject
    lateinit var localRepository: LocalRepository

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    init {
        DaggerDataManagerComponent
            .builder()
            .applicationComponent(context.applicationComponent())
            .build()
            .inject(this)
    }

    suspend fun getMovies(successHandler: (List<Movie>) -> Unit, failedHandler: () -> Unit) {
        runBlocking {
            try {
                val apiAsync = async { apiServices.getMovies().await() }
                val response = apiAsync.await()
                if (response.isSuccessful && response.body()!!.movies.isNotEmpty()) {
                   val list = response.body()!!.movies
                    list.forEach {
                        localRepository.insert(it)
                    }
                    successHandler.invoke(list)
                } else {
                    successHandler.invoke(localRepository.getAllMovies())
                }
            } catch (e: Exception) {
                failedHandler.invoke()
            }
        }
    }

    fun getLastVisitedDate(): String {
        val key = "PREFS_LAST_VISITED"
        val dateLastVisited = sharedPreferences.getString(key, "")
        if (!dateLastVisited.isNullOrEmpty()) return dateLastVisited

        val formatter = SimpleDateFormat("MMM dd, yyyy")
        val date = formatter.format(Date())

        sharedPreferences
            .edit()
            .putString(key, date)
            .commit()

        return date
    }
}