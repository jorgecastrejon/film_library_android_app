package com.jcastrejon.filmlibrary.ui.presenters.movies

import com.jcastrejon.filmlibrary.R
import com.jcastrejon.filmlibrary.domain.models.*
import com.jcastrejon.filmlibrary.domain.usecases.GetMoviesUseCase
import com.jcastrejon.filmlibrary.ui.contracts.movies.MoviesContract
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * Presenter of the Movies Activity
 */
internal open class MoviesPresenter(private val view: MoviesContract.View,
                                    private val getMovies: GetMoviesUseCase): MoviesContract.Presenter {

    override fun update() {
        view.showProgress()
        view.hideMoviesMessage()
        requestMovies()
    }

    override fun onMovieClicked(movieId: Int) = view.navigateToMovieDetailActivity(movieId)

    /**
     * Perform the getMovies operation
     */
    private fun requestMovies() = launch(UI) {
        val result = async { getMovies() }.await()

        when (result) {
            is Success -> { showMovies(result.value) }
            is Error -> { handleErrors(result.value) }
        }
        view.hideProgress()
    }

    /**
     * Handle the success result
     *
     * @param movies
     */
    private fun showMovies(movies: List<Movie>) {
        when {
            movies.isEmpty() -> { showMessage(R.string.empty_movies_message) }
            else -> view.showMovies(movies)
        }
        view.hideProgress()
    }

    /**
     * Handle errors
     *
     * @param error
     */
    private fun handleErrors(error: DomainError) {
        when (error) {
            InternetError -> { showMessage(R.string.internetError) }
            UnknownError -> { showMessage(R.string.unknownError) }
        }
    }

    /**
     * Show a message instead the list of movies
     *
     * @param textRest
     */
    private fun showMessage(textRest: Int) {
        view.setMoviesMessage(textRest)
        view.showMoviesMessage()
        view.hideMovies()
    }
}