package com.kotlin.training.myapplication.mvp.collaborators

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kotlin.training.myapplication.mvp.MainActivity
import com.kotlin.training.myapplication.R
import com.kotlin.training.myapplication.mvp.base.BaseFragment
import com.kotlin.training.myapplication.mvp.collaborator.CollaboratorActivity
import kotlinx.android.synthetic.main.fragment_colaborators.*

class CollaboratorsFragment : BaseFragment(), CollaboratorsMvpView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_colaborators, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var presenter = CollaboratorsPresenter(this)
        presenter.fetchCollaborators()
    }

    override fun showCollaborators(list: ArrayList<User>) {
        var adapter = CollaboratorsAdapter(context!!, list)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            startActivity(CollaboratorActivity.start(activity as MainActivity, list[position]))
        }
    }

    override fun showEmptyMessage() {
        Toast.makeText(context, "Empty list!", Toast.LENGTH_LONG).show()
    }

}