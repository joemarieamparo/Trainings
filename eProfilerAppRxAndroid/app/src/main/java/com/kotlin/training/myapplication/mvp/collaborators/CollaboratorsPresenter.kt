package com.kotlin.training.myapplication.mvp.collaborators

import com.kotlin.training.myapplication.api.ServiceManager
import com.kotlin.training.myapplication.mvp.base.BasePresenter
import com.kotlin.training.myapplication.mvp.base.BaseSubscriber

class CollaboratorsPresenter(var view: CollaboratorsMvpView)
    : BasePresenter<CollaboratorsMvpView>() {

    private val userApiService by lazy {
        ServiceManager().getUserApiService()
    }
    fun fetchCollaborators() {
        view.showLoader()
        observe(userApiService.getUsers(),
            object: BaseSubscriber<UserResponse> {
                override fun onSuccess(response: UserResponse) {
                    if (!response.users.isEmpty()) view.showCollaborators(response.users)
                    else view.showEmptyMessage()
                    view.hideLoader()
                }

                override fun onError() {
                    view.showTechnicalError()
                    view.hideLoader()
                }
        })
    }
}