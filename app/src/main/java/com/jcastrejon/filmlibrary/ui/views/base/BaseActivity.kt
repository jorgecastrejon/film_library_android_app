package com.jcastrejon.filmlibrary.ui.views.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.jcastrejon.filmlibrary.ui.contracts.base.BaseContract

/**
 * Base activity
 */
internal abstract class BaseActivity : AppCompatActivity(), BaseContract.View {

    abstract val layoutId: Int
    abstract val toolbarView: Toolbar

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}