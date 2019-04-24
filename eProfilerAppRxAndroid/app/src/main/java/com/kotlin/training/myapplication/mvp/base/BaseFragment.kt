package com.kotlin.training.myapplication.mvp.base

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.Fragment
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog

open class BaseFragment : Fragment(), MvpView {

    private lateinit var progressDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = activity!!.indeterminateProgressDialog("Loading... Please wait...")
    }

    override fun showLoader() {
        hideLoader()
        progressDialog.show()
    }

    override fun hideLoader() {
        if (progressDialog.isShowing) progressDialog.dismiss()
    }

    override fun showTechnicalError() {
        activity!!.alert("Something went wrong. Please come back later.") {
            title("Oops!")
            yesButton {  }
        }.show()
    }

}