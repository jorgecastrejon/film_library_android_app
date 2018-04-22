package com.jcastrejon.filmlibrary.ui.contracts.movies

import com.jcastrejon.filmlibrary.domain.models.Movie
import com.jcastrejon.filmlibrary.ui.contracts.base.BaseContract

/**
 * Movies Contract
 */
internal class MoviesContract {

    /**
     * View
     */
    interface View : BaseContract.View {

        /**
         * Open the movie detail activity
         */
        fun navigateToMovieDetailActivity(movieId: Int)

        /**
         * Show a message informing of something
         */
        fun showMoviesMessage()

        /**
         * Show the message
         */
        fun hideMoviesMessage()

        /**
         * Set the message to show
         */
        fun setMoviesMessage(textRes: Int)

        /**
         * Show the list of movies
         *
         * @param movies
         */
        fun showMovies(movies: List<Movie>)

        /**
         * Hide the list of movies
         */
        fun hideMovies()

    }

    /**
     * Presenter
     */
    interface Presenter : BaseContract.Presenter {

        /**
         * Update the data
         */
        fun update()

        /**
         * On movie clicked
         *
         * @param movieId
         */
        fun onMovieClicked(movieId: Int)
    }
}