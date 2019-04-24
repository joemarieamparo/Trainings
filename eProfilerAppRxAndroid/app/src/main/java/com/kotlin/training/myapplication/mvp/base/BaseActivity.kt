package com.kotlin.training.myapplication.mvp.base

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog

open class BaseActivity : AppCompatActivity(), BaseMvpView {

    private lateinit var progressDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = indeterminateProgressDialog("Loading... Please wait...")
    }

    override fun showLoader() {
        hideLoader()
        progressDialog.show()
    }

    override fun hideLoader() {
        if (progressDialog.isShowing) progressDialog.dismiss()
    }

    override fun showTechnicalError() {
        alert("Something went wrong. Please come back later.") {
            title("Oops!")
            yesButton {  }
        }.show()
    }

}