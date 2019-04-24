package com.kotlin.training.myapplication.mvp.collaborators

import com.kotlin.training.myapplication.mvp.base.BaseMvpView


interface CollaboratorsMvpView : BaseMvpView {
    fun showCollaborators(list: ArrayList<User>)
    fun showEmptyMessage()
}