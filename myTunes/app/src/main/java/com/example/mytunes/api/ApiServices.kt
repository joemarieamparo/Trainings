package com.example.mytunes.api

import com.example.mytunes.api.models.ITunesSearchResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * ApiServices contains all api calls use in the app.
 * Structure is following the Retrofit library being integrated in the application
 */
interface ApiServices {

    @GET("search")
    fun getMovies(@Query("term") term: String = "star",
                  @Query("country") country: String = "au",
                  @Query("media") media: String = "movie")
            : Deferred<Response<ITunesSearchResponse>>
}
