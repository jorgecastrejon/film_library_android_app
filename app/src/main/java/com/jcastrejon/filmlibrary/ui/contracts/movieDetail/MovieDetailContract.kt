package com.jcastrejon.filmlibrary.ui.contracts.movieDetail

import com.jcastrejon.filmlibrary.domain.models.Movie
import com.jcastrejon.filmlibrary.ui.contracts.base.BaseContract

/**
 * Movies detail screen contract
 */
internal class MovieDetailContract {

    /**
     * View
     */
    interface View : BaseContract.View {

        /**
         * Close the current activity
         */
        fun closeActivity()

        /**
         * Fill the screen with the movie data
         *
         * @param movie
         */
        fun showMovie(movie: Movie)

        /**
         * Show an error
         *
         * @param error
         */
        fun showError(error: Int)
    }

    /**
     * Presenter
     */
    interface Presenter : BaseContract.Presenter {

        /**
         * Save the movie id for further operations
         *
         * @param movieId
         */
        fun initializePresenter(movieId: Int?)

        /**
         * Update the data
         */
        fun update()
    }
}