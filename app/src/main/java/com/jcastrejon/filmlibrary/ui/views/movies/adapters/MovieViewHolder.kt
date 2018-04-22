package com.jcastrejon.filmlibrary.ui.views.movies.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import com.jcastrejon.filmlibrary.common.BASE_IMAGE_URL
import com.jcastrejon.filmlibrary.domain.models.Movie
import com.jcastrejon.filmlibrary.extensions.justYear
import com.jcastrejon.filmlibrary.extensions.setImageFromUrl
import kotlinx.android.synthetic.main.movie_layout.view.*

/**
 * ViewHolder rendering a movie showed in MoviesActivity
 */
internal class MovieViewHolder (itemView: View, private val func: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

    /**
     * Set all the movie information
     *
     * @param movie
     */
    fun setMovie(movie: Movie) {
        with(itemView) {
            setOnClickListener { func(movie.id) }
            movie_image.setImageFromUrl(BASE_IMAGE_URL + movie.backdrop)
            movie_title.text = movie.title
            movie_rating.text = movie.rate.toString()
            movie_release_date.text = movie.date.justYear()
        }
    }
}
