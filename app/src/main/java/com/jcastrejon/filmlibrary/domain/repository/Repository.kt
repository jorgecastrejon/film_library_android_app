package com.jcastrejon.filmlibrary.domain.repository

import com.jcastrejon.filmlibrary.domain.models.DomainError
import com.jcastrejon.filmlibrary.domain.models.Movie
import com.jcastrejon.filmlibrary.domain.models.Result

/**
 * Repository guideline
 */
internal interface Repository {

    /**
     * Get the movies
     */
    fun getMovies(): Result<List<Movie>, DomainError>

    /**
     * Get the movie by id
     *
     * @param movieId
     */
    fun getMovieById(movieId: Int): Result<Movie, DomainError>
}