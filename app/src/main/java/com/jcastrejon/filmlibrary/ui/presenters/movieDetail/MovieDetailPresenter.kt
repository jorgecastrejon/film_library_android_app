package com.jcastrejon.filmlibrary.ui.presenters.movieDetail

import com.jcastrejon.filmlibrary.R
import com.jcastrejon.filmlibrary.domain.models.Movie
import com.jcastrejon.filmlibrary.domain.models.Success
import com.jcastrejon.filmlibrary.domain.usecases.GetMovieByIdUseCase
import com.jcastrejon.filmlibrary.ui.contracts.movieDetail.MovieDetailContract
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * Presenter of the MovieDetailActivity
 */
internal class MovieDetailPresenter(private val view: MovieDetailContract.View,
                                    private val getMovieById: GetMovieByIdUseCase): MovieDetailContract.Presenter {

    private var movieId: Int = 0

    override fun initializePresenter(movieId: Int?) {
        if (movieId != null) {
            this.movieId = movieId
        } else {
            view.closeActivity()
        }
    }

    override fun update() {
        view.showProgress()
        requestMovie()
    }

    private fun requestMovie() = launch(UI) {
        val result = async { getMovieById(movieId) }.await()

        when (result) {
            is Success -> { showMovie(result.value) }
            is Error -> { view.showError(R.string.unknownError) }
        }
    }

    private fun showMovie(movie: Movie) {
        view.showMovie(movie)
        view.hideProgress()
    }
}