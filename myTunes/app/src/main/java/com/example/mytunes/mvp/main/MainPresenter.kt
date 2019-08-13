package com.example.mytunes.mvp.main

import android.content.Context
import com.example.mytunes.api.DataManager
import com.example.mytunes.api.models.Movie
import com.example.mytunes.dependencyinjection.modules.ActivityComponent
import com.example.mytunes.mvp.MainMvpView
import com.example.mytunes.mvp.base.BasePresenter
import com.example.mytunes.utils.isConnected
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainPresenter(var context: Context) : BasePresenter<MainMvpView>(context){

    @Inject
    lateinit var dataManager: DataManager

    override fun loadData() {
        getMovies()
        mvpView!!.setDateLastVisited(dataManager.getLastVisitedDate())
    }

    override fun injectComponent(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    fun getMovies(){
        scope.launch {
            mvpView!!.showLoader()
            val successHandler = { movies : List<Movie> ->
                when (movies.isEmpty()) {
                    true -> mvpView!!.showEmptyList()
                    else -> mvpView!!.showMovies(movies.toMovieItemAdapter())
                }
                mvpView!!.hideLoader()
            }

            val failedHandler = {
                when (context.isConnected()) {
                    false -> mvpView!!.showConnectionError()
                    else -> mvpView!!.showTechnicalError()
                }
                mvpView!!.hideLoader()
            }
            dataManager.getMovies(successHandler, failedHandler)
        }
    }
}

private fun <E> Collection<E>.toMovieItemAdapter(): List<MovieAdapter.MovieItem> {
    var list = mutableListOf<MovieAdapter.MovieItem>()
    this.forEach {
        list.add(MovieAdapter.MovieItem(it as Movie))
    }
    return list
}
