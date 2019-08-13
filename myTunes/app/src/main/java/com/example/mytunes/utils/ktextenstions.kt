package com.example.mytunes.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.mytunes.app.MyTunesApplication
import com.example.mytunes.dependencyinjection.modules.ActivityComponent
import com.example.mytunes.dependencyinjection.modules.ApplicationComponent

fun Activity.myApplication(): MyTunesApplication = applicationContext.myApplication()

fun Activity.activityComponent(): ActivityComponent = applicationContext.activityComponent()

fun Context.myApplication(): MyTunesApplication = this.applicationContext as MyTunesApplication

fun Context.applicationComponent(): ApplicationComponent = this.myApplication().applicationComponent

fun Context.activityComponent(): ActivityComponent = this.myApplication().activityComponent

fun Context.isConnected(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

fun Context.createAlertDialog(title: String, msg: String, posMsg: Int, posAction: () -> Unit,
                              negMsg: Int, negAction: () -> Unit, icon: Int): AlertDialog {
    var builder = AlertDialog.Builder(this)

    if (!title.isNullOrEmpty()) builder.setTitle(title)
    if (!msg.isNullOrEmpty()) builder.setMessage(msg)

    builder.setPositiveButton(posMsg) { dialog, _ ->
        dialog.dismiss()
        posAction.invoke()
    }

    builder.setNegativeButton(negMsg) { dialog, _ ->
        dialog.dismiss()
        negAction.invoke()
    }
    builder.setIcon(icon)

    return builder.create()
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View. VISIBLE
}
