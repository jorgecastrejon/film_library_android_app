package com.jcastrejon.filmlibrary.ui.views.base

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.jcastrejon.filmlibrary.R
import com.jcastrejon.filmlibrary.ui.contracts.base.BaseContract

/**
 * Base activity
 */
internal abstract class BaseActivity : AppCompatActivity(), BaseContract.View {

    abstract val layoutId: Int
    abstract val toolbarView: Toolbar
    private val progress: AlertDialog by lazy { createProgressDialog() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        setSupportActionBar(toolbarView)
    }

    override fun onResume() {
        super.onResume()
        update()
    }

    open fun update() {}

    override fun showProgress() {
        progress.show()
    }

    override fun hideProgress() {
        progress.dismiss()
    }

    private fun createProgressDialog() =
        AlertDialog.Builder(this)
                .setView(layoutInflater.inflate(R.layout.progress_layout, null))
                .setCancelable(false)
                .create()
                .apply { window.decorView.setBackgroundResource(android.R.color.transparent) }
}