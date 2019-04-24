package com.kotlin.training.myapplication.mvp.collaborator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kotlin.training.myapplication.R
import com.kotlin.training.myapplication.mvp.base.BaseActivity
import com.kotlin.training.myapplication.mvp.collaborators.User
import kotlinx.android.synthetic.main.activity_collaborator.*

class CollaboratorActivity : BaseActivity(), CollaboratorMvpView {

    companion object {
        const val EXTRA_COLLABORATOR = "EXTRA_COLLABORATOR"

        fun start(activity: Activity, user: User) : Intent {
            var intent =  Intent(activity, CollaboratorActivity::class.java)
            intent.putExtra(EXTRA_COLLABORATOR, user)
            return  intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collaborator)
        CollaboratorPresenter(this).handleIntent(intent)
    }

    override fun showCollaboratorInfo(user: User) {
        Glide.with(this)
            .load(user.picture.large)
            .apply(RequestOptions().circleCrop())
            .into(imageView)
        name.text = "${user.name.first.capitalize()} ${user.name.last.capitalize()}"
        address.text = "${user.location.address()}, ${user.nationality}"
        email.text = user.email
    }
}