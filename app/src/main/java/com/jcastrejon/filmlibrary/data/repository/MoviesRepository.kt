package com.jcastrejon.filmlibrary.data.repository

import com.jcastrejon.filmlibrary.data.sources.MoviesDataSource
import com.jcastrejon.filmlibrary.domain.models.*
import com.jcastrejon.filmlibrary.domain.repository.Repository

/**
 * Implementation of the repository
 */
internal class MoviesRepository (private val remoteDataSource: MoviesDataSource): Repository {

    companion object {
        private const val ACCEPTABLE_TIME = 30 * 1000
    }

    private val cache: MutableList<Movie> = ArrayList()
    private var lastUpdate = 0L

    override fun getMovies(): Result<List<Movie>, DomainError> =
        if (cache.isEmpty() || !isCacheUpdated()) {
            val moviesResult = remoteDataSource.getMovies()

            if (moviesResult is Success) {
                populateResult(moviesResult.value)
            }
            moviesResult
        } else {
            Success(cache)
        }

    /**
     * Replace the cache data and update the lastUpdate var
     */
    private fun populateResult(movies: List<Movie>) {
        cache.clear()
        cache.addAll(movies)
        lastUpdate = System.currentTimeMillis()
    }

    /**
     * Establish if the cache is updated
     */
    private fun isCacheUpdated() = (System.currentTimeMillis() - lastUpdate) < ACCEPTABLE_TIME
}