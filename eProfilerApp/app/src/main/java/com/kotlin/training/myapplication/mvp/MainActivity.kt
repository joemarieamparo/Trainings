package com.kotlin.training.myapplication.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.training.myapplication.R
import com.kotlin.training.myapplication.addFragment
import com.kotlin.training.myapplication.mvp.collaborators.CollaboratorsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collaborators)
        addFragment(CollaboratorsFragment())
    }
}

