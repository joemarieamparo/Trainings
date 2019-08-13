package com.example.mytunes.mvp.base

import android.content.Context
import androidx.annotation.CallSuper
import com.example.mytunes.mvp.BaseMvpView
import com.example.mytunes.mvp.Presenter
import com.example.mytunes.utils.activityComponent
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<T : BaseMvpView> (private val context: Context) :
    Presenter<T> {

    protected var mvpView: T ?= null
        get() = field

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    protected val scope = CoroutineScope(coroutineContext)

    @CallSuper
    override fun attachView(mvpView: T) {
        this.mvpView = mvpView
        injectComponent(context.activityComponent())
        loadData()
    }

    @CallSuper
    override fun detachView() {
        mvpView = null
        parentJob.cancel()
    }
}
