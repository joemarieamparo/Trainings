package com.kotlin.training.myapplication.mvp.collaborator

import android.content.Intent
import com.kotlin.training.myapplication.mvp.collaborators.User


class CollaboratorPresenter {

    var view: CollaboratorMvpView

    constructor(view: CollaboratorMvpView) {
        this.view = view
    }

    fun handleIntent(intent: Intent?) {
        if (intent == null) return

        var user = intent.getParcelableExtra(CollaboratorActivity.EXTRA_COLLABORATOR) as User
        if (user != null) view.showCollaboratorInfo(user)
        else view.showTechnicalError()
    }
}