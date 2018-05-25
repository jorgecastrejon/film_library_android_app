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
        private var INSTANCE: MoviesRepository? = null

        /**
         * Returns a instance of this class
         *
         * @param dataSource
         */
        fun getInstance(dataSource: MoviesDataSource): MoviesRepository {
            return INSTANCE ?: MoviesRepository(dataSource).apply { INSTANCE = this }
        }
    }

    private val cache: LinkedHashMap<Int, Movie> = LinkedHashMap()
    private var lastUpdate = 0L

    override fun getMovies(): Result<List<Movie>, DomainError> =
        if (cache.isEmpty() || !isCacheUpdated()) {
            val moviesResult = remoteDataSource.getMovies()

            moviesResult.also {
                if (moviesResult is Success) {
                    populateMovies(moviesResult.value)
                }
            }
        } else {
            Success(ArrayList(cache.values))
        }

    override fun getMovieById(movieId: Int): Result<Movie, DomainError> =
        if (cache.isEmpty() || !cache.containsKey(movieId) || !isCacheUpdated()) {
            remoteDataSource.getMovieById(movieId)
        } else {
            cache[movieId]?.let { Success(cache[movieId]!!) } ?: Error(UnknownError)
        }

    /**
     * Replace the cache data and update the lastUpdate var
     *
     * @param movies
     */
    private fun populateMovies(movies: List<Movie>) {
        cache.clear()
        cache.putAll(movies.map { movie -> movie.id to movie })
        lastUpdate = System.currentTimeMillis()
    }

    /**
     * Establish if the cache is updated
     */
    private fun isCacheUpdated() = (System.currentTimeMillis() - lastUpdate) < ACCEPTABLE_TIME
}