package com.jcastrejon.filmlibrary.ui.views.movies

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.jcastrejon.filmlibrary.Injection
import com.jcastrejon.filmlibrary.R
import com.jcastrejon.filmlibrary.domain.models.Movie
import com.jcastrejon.filmlibrary.domain.usecases.GetMoviesUseCase
import com.jcastrejon.filmlibrary.extensions.hide
import com.jcastrejon.filmlibrary.extensions.show
import com.jcastrejon.filmlibrary.ui.contracts.movies.MoviesContract
import com.jcastrejon.filmlibrary.ui.presenters.movies.MoviesPresenter
import com.jcastrejon.filmlibrary.ui.views.base.BaseActivity
import com.jcastrejon.filmlibrary.ui.views.movieDetail.MovieDetailActivity
import com.jcastrejon.filmlibrary.ui.views.movies.adapters.MoviesAdapter
import kotlinx.android.synthetic.main.content_movies.*
import kotlinx.android.synthetic.main.toolbar_layout.*

internal class MoviesActivity : BaseActivity(), MoviesContract.View {

    override val layoutId: Int = R.layout.activity_movies
    override val toolbarView: Toolbar
        get() = toolbar

    private lateinit var adapter: MoviesAdapter
    private val presenter: MoviesContract.Presenter = MoviesPresenter(this, GetMoviesUseCase(Injection.provideRepository()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeAdapter()
        initializeRecyclerView()
    }

    override fun update() {
        presenter.update()
    }

    override fun showMovies(movies: List<Movie>) {
        adapter.clear()
        adapter.addAll(movies)
        adapter.notifyDataSetChanged()
    }

    override fun hideMovies() {
        recycler_view.hide()
    }

    override fun showMoviesMessage() {
        movies_message.show()
    }

    override fun hideMoviesMessage() {
        movies_message.hide()
    }

    override fun setMoviesMessage(textRes: Int) {
        movies_message.text = getString(textRes)
    }

    override fun navigateToMovieDetailActivity(movieId: Int) {
        MovieDetailActivity.openMovieDetailActivity(this, movieId)
    }

    /**
     * Initialize the adapter
     */
    private fun initializeAdapter() {
        adapter = MoviesAdapter { movieId -> presenter.onMovieClicked(movieId) }
    }

    /**
     * Initialize the recyclerView
     */
    private fun initializeRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter
    }
}
