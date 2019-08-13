package com.example.mytunes.utils

import android.util.Log
import com.example.mytunes.R
import com.example.mytunes.api.models.Movie
import kotlin.math.abs

class ImageUtils {


    companion object {
        private val placeHolderImageId = listOf(R.drawable.smiley1, R.drawable.smiley2, R.drawable.smiley3,
            R.drawable.smiley4, R.drawable.smiley5, R.drawable.smiley6, R.drawable.smiley7, R.drawable.smiley8,
            R.drawable.smiley9, R.drawable.smiley10)

        fun getBestArtworkUrl(movie : Movie): String? {
            if (!movie.artworkUrl600.isNullOrEmpty()) return movie.artworkUrl600

            if (!movie.artworkUrl100.isNullOrEmpty()) return movie.artworkUrl100

            if (!movie.artworkUrl60.isNullOrEmpty()) return movie.artworkUrl60

            if (!movie.artworkUrl30.isNullOrEmpty()) return movie.artworkUrl30

            return null
        }

        fun getPlaceHolderImage(position: Int): Int {
            return placeHolderImageId[(abs(position) % 10)]

        }
    }

}