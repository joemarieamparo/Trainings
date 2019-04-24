package com.kotlin.training.myapplication.mvp.collaborators

import com.kotlin.training.myapplication.api.ServiceManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CollaboratorsPresenter {

    var view: CollaboratorsMvpView

    constructor(view: CollaboratorsMvpView) {
        this.view = view
    }

    fun fetchCollaborators() {
        view.showLoader()
        ServiceManager()
            .getUserApiService()
            .getUsers()
            .enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                view.showTechnicalError()
                view.hideLoader()
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    var userResponse = response.body() as UserResponse
                    view.showCollaborators(userResponse.users)
                } else {
                    view.showEmptyMessage()
                }
                view.hideLoader()
            }
        })
    }
}