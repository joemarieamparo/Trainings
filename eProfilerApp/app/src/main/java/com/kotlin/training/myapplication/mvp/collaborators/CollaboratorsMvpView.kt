package com.kotlin.training.myapplication.mvp.collaborators

import com.kotlin.training.myapplication.mvp.base.MvpView


interface CollaboratorsMvpView : MvpView {
    fun showCollaborators(list: ArrayList<User>)
    fun showEmptyMessage()
}