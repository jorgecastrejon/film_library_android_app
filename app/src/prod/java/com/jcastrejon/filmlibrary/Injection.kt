package com.jcastrejon.filmlibrary

import com.jcastrejon.filmlibrary.data.repository.MoviesRepository
import com.jcastrejon.filmlibrary.data.sources.MoviesApiClient

/**
 * Allow the injection of the production repository implementation
 */
internal object Injection {

    /**
     * Provides the Movies Repository
     */
    fun provideRepository(): MoviesRepository {
        return MoviesRepository.getInstance(MoviesApiClient())
    }
}