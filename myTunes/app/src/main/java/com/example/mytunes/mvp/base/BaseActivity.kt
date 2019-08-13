package com.example.mytunes.mvp.base

import android.os.Bundle
import com.example.mytunes.dependencyinjection.modules.ActivityComponent
import com.example.mytunes.mvp.BaseMvpView
import com.example.mytunes.utils.activityComponent
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mytunes.R

open class BaseActivity : AppCompatActivity(), BaseMvpView {

    lateinit var activityComponent: ActivityComponent
    private lateinit var dialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = this.activityComponent()
        dialog = ProgressDialog(this, R.style.AppCompatAlertDialogStyle)
        dialog.setMessage("Please wait ...")
        dialog.setCancelable(false)
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
    }

    override fun showLoader() {
        dialog.show()

    }

    override fun hideLoader() {
        if (dialog.isShowing) dialog.dismiss()
    }

    override fun showConnectionError() {
        //Generic behavior here
    }

    override fun showTechnicalError() {
        //Generic behavior here
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog.dismiss()
    }
}