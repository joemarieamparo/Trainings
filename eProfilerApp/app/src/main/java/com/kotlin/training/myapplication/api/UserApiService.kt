package com.kotlin.training.myapplication.api

import com.kotlin.training.myapplication.mvp.collaborators.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface UserApiService {
    @GET("api/?results=20")
    fun getUsers() : Call<UserResponse>
}