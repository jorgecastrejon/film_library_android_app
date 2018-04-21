package com.jcastrejon.filmlibrary.ui.views.movies

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.jcastrejon.filmlibrary.R
import com.jcastrejon.filmlibrary.ui.contracts.movies.MoviesContract
import com.jcastrejon.filmlibrary.ui.views.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar_layout.*

internal class MoviesActivity : BaseActivity(), MoviesContract.View {

    override val layoutId: Int = R.layout.activity_movies
    override val toolbarView: Toolbar
        get() = toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeAdapter()
        initializeRecyclerView()
    }

    override fun update() {
    }


    private fun initializeAdapter() {
    }

    private fun initializeRecyclerView() {

    }
}
