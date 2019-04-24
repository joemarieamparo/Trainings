package com.kotlin.training.myapplication.mvp.collaborator

import com.kotlin.training.myapplication.mvp.base.BaseMvpView
import com.kotlin.training.myapplication.mvp.collaborators.User

interface CollaboratorMvpView : BaseMvpView {
    fun showCollaboratorInfo(user: User)
}