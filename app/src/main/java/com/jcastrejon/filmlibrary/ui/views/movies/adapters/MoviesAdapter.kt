package com.jcastrejon.filmlibrary.ui.views.movies.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jcastrejon.filmlibrary.R
import com.jcastrejon.filmlibrary.domain.models.Movie

/**
 * Adapter to show a list of movies
 */
internal class MoviesAdapter (private val func: (Int) -> Unit): RecyclerView.Adapter<MovieViewHolder>() {

    private val movies: MutableList<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_layout, parent, false)

        return MovieViewHolder(view, func)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val book = movies[position]
        holder.setMovie(book)
    }

    override fun getItemCount(): Int = movies.size

    /**
     * Add a collections of movies
     *
     * @param collection
     */
    fun addAll(collection: Collection<Movie>) = movies.addAll(collection)

    /**
     * Clear the adapter list of movies
     */
    fun clear() = movies.clear()
}