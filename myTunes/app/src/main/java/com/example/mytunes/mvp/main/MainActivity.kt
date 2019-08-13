package com.example.mytunes.mvp.main

import android.os.Bundle
import com.example.mytunes.mvp.MainMvpView
import com.example.mytunes.mvp.base.BaseActivity
import javax.inject.Inject
import androidx.appcompat.app.AlertDialog
import com.example.mytunes.R
import com.example.mytunes.api.models.Movie
import com.example.mytunes.utils.createAlertDialog
import com.example.mytunes.mvp.detailscreen.MovieDetailsActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.mytunes.utils.gone
import com.example.mytunes.utils.show
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity(), MainMvpView {

    @Inject
    lateinit var presenter: MainPresenter

    private lateinit var moviesAdapter: MovieAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        activityComponent.inject(this)
        presenter.attachView(this)

        toolbar.title = getString(R.string.mytunes_movies)
        setSupportActionBar(toolbar)

        moviesAdapter = MovieAdapter (getInitialPlaceHolderList(), onItemClickListener)
        viewManager = LinearLayoutManager(this)
        (recycler_view as RecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = moviesAdapter

        }
    }

    override fun setDateLastVisited(date: String) {
        textview_datelastvisited.text = "Last visit on $date"
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    @OnClick(R.id.empty_view)
    fun onLoadClicked() {
        recycler_view.show()
        empty_view.gone()
        presenter.getMovies()
    }

    private fun getInitialPlaceHolderList(): MutableList<MovieAdapter.Item> {
        var list = mutableListOf<MovieAdapter.Item>()
        list.add(MovieAdapter.PlaceHolderItem())
        list.add(MovieAdapter.PlaceHolderItem())
        list.add(MovieAdapter.PlaceHolderItem())
        list.add(MovieAdapter.PlaceHolderItem())
        return list
    }

    override fun showMovies(items: List<MovieAdapter.MovieItem>) {
        recycler_view.show()
        moviesAdapter.update(items)
    }

    override fun showEmptyList() {
        recycler_view.gone()
        empty_view.show()
    }

    override fun showConnectionError() {
        var dialog: AlertDialog = createAlertDialog(
            getString(R.string.connection_error_title),
            getString(R.string.connection_error_msg),
            android.R.string.yes, {
                presenter.getMovies()
            },
            android.R.string.no, {
                showReloadView()
            },
            android.R.drawable.ic_dialog_alert
        )
        dialog.show()
    }

    override fun showTechnicalError() {
        var dialog: AlertDialog = createAlertDialog(
            getString(R.string.technical_error_title),
            getString(R.string.technical_error_msg),
            android.R.string.yes, {
                presenter.getMovies()
            },
            android.R.string.no, {
                showReloadView()
            },
            android.R.drawable.ic_dialog_alert
        )
        dialog.show()
    }

    private fun showReloadView() {
        showEmptyList()
        empty_view.text = getString(R.string.reload_msg)
    }

    private val onItemClickListener = { movie: Movie ->
        run {
            MovieDetailsActivity.start(this, movie)
        }
    }
}