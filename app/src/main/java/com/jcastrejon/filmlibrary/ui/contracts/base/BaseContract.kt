package com.jcastrejon.filmlibrary.ui.contracts.base

/**
 * Base Contract Presenter - View
 */
internal class BaseContract {

    /**
     * Base View
     */
    interface View {

        /**
         * Show the progressbar
         */
        fun showProgress()

        /**
         * Hide the progressbar
         */
        fun hideProgress()
    }

    /**
     * Base Presenter
     */
    interface Presenter
}