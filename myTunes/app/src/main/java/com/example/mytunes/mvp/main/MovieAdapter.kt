package com.example.mytunes.mvp.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytunes.R
import com.example.mytunes.api.models.Movie
import com.example.mytunes.dependencyinjection.modules.GlideApp
import com.example.mytunes.utils.ImageUtils
import kotlinx.android.synthetic.main.item_movie_layout.view.*
import kotlinx.android.synthetic.main.item_movie_layout.view.textview_price

class MovieAdapter (private var items: MutableList<Item> = mutableListOf(),
                    private val onItemClickListener: (Movie) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MOVIE_ITEM -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_movie_layout, parent, false)
                MovieViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_movie_placeholder_layout, parent, false)
                PlaceholderViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var movie = items[position]
        (holder as ViewHolderBinder).onBindView(movie, position)

        if (holder is MovieViewHolder) {
            holder.itemView.setOnClickListener {
                onItemClickListener(movie.getMovieItem()!!)
            }
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return items[position].getType()
    }

    fun update(items: List<Item>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    interface Item {
        fun getType(): Int
        fun getMovieItem(): Movie?
    }

    interface ViewHolderBinder {
        fun onBindView (item: Item,  position: Int)
    }

    class MovieItem (var movie: Movie) : Item {

        override fun getMovieItem(): Movie? {
            return this.movie
        }

        override fun getType(): Int {
            return MOVIE_ITEM
        }

    }

    class MovieViewHolder(view: View): RecyclerView.ViewHolder(view), ViewHolderBinder  {



        override fun onBindView(item: Item,  position: Int) {
            var movie = item.getMovieItem()!!

            var imageUrl = ImageUtils.getBestArtworkUrl(movie)

            val placeHolderImageId = ImageUtils.getPlaceHolderImage(position)
            GlideApp.with(itemView.context)
                .load(imageUrl)
                .error(placeHolderImageId)
                .into(itemView.imageview_artwork)

            itemView.textview_name.text = movie.trackName
            itemView.textview_genre.text = movie.primaryGenreName
            itemView.textview_price.text = "Price: ${movie.trackPrice}"
        }
    }

    class PlaceHolderItem : Item {
        override fun getMovieItem(): Movie? {
            return null
        }

        override fun getType(): Int {
            return PLACEHOLDER_ITEM
        }
    }

    class PlaceholderViewHolder(view: View) : RecyclerView.ViewHolder(view), ViewHolderBinder {

        override fun onBindView(item: Item,  position: Int) { }
    }

    companion object {
        const val MOVIE_ITEM: Int = 1
        const val PLACEHOLDER_ITEM = 2
    }
}