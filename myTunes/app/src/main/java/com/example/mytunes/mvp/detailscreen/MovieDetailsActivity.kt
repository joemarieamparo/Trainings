package com.example.mytunes.mvp.detailscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.example.mytunes.R
import com.example.mytunes.api.models.Movie
import com.example.mytunes.dependencyinjection.modules.GlideApp
import com.example.mytunes.utils.ImageUtils
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.content_movie_details.*
import android.view.MenuItem


class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        var movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        toolbar.title = movie.trackName
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var imageUrl = ImageUtils.getBestArtworkUrl(movie)
        if (!imageUrl.isNullOrEmpty())
            GlideApp.with(this).load(imageUrl).into(imageview_artwork)

        movie.primaryGenreName?.let {
            textview_genre.text = "Genre: $it"
        }

        movie.trackPrice?.let {
            textview_price.text = "Price: $it"
        }

        movie.longDescription?.let {
            textview_description.text = it
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return false
    }

    companion object {
        private const val  EXTRA_MOVIE = "com.example.mytunes.mvp.detailscreen.EXTRA_MOVE"

        fun start(context: Context, movie: Movie) {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(EXTRA_MOVIE, movie)
            context.startActivity(intent)

        }
    }
}
