package com.example.mytunes.mvp

import com.example.mytunes.dependencyinjection.modules.ActivityComponent
import com.example.mytunes.mvp.main.MovieAdapter

interface BaseMvpView {

    fun showLoader()

    fun hideLoader()

    fun showConnectionError()

    fun showTechnicalError()

}

interface Presenter<V : BaseMvpView> {

    fun attachView(mvpView: V)

    fun detachView()

    fun loadData()

    fun injectComponent(activityComponent: ActivityComponent)

}

interface MainMvpView : BaseMvpView {

    fun showMovies(items: List<MovieAdapter.MovieItem>)
    fun showEmptyList()
    fun setDateLastVisited(date: String)
}
