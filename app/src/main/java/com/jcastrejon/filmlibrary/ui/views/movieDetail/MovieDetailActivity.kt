package com.jcastrejon.filmlibrary.ui.views.movieDetail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.jcastrejon.filmlibrary.Injection
import com.jcastrejon.filmlibrary.R
import com.jcastrejon.filmlibrary.common.BASE_IMAGE_URL
import com.jcastrejon.filmlibrary.domain.models.Movie
import com.jcastrejon.filmlibrary.domain.usecases.GetMovieByIdUseCase
import com.jcastrejon.filmlibrary.extensions.setImageFromUrl
import com.jcastrejon.filmlibrary.extensions.show
import com.jcastrejon.filmlibrary.ui.contracts.movieDetail.MovieDetailContract
import com.jcastrejon.filmlibrary.ui.presenters.movieDetail.MovieDetailPresenter
import com.jcastrejon.filmlibrary.ui.views.base.BaseActivity
import kotlinx.android.synthetic.main.content_movie_detail.*
import kotlinx.android.synthetic.main.toolbar_layout.*

internal class MovieDetailActivity : BaseActivity(), MovieDetailContract.View {

    companion object {
        private const val MOVIE_ID_ARG = "movie_id_arg"

        fun openMovieDetailActivity(activity: Activity, movieId: Int) {
            val intent = Intent(activity, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_ID_ARG, movieId)
            activity.startActivity(intent)
        }
    }

    override val layoutId: Int = R.layout.activity_movie_detail
    override val toolbarView: Toolbar
        get() = toolbar

    private val presenter: MovieDetailContract.Presenter = MovieDetailPresenter(this, GetMovieByIdUseCase(Injection.provideRepository()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.initializePresenter(intent?.extras?.getInt(MOVIE_ID_ARG))
    }

    override fun update() {
        presenter.update()
    }

    override fun closeActivity() {
        finish()
    }

    override fun showMovie(movie: Movie) {
        with(movie) {
            movie_title.text = title
            movie_description.text = description
            movie_image.setImageFromUrl(BASE_IMAGE_URL + movie.poster)
            movie_title.show()
            movie_description.show()
            movie_image.show()
        }
    }

    override fun showError(error: Int) {
        Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()
        closeActivity()
    }
}
