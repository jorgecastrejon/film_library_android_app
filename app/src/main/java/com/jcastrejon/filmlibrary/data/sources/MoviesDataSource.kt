package com.jcastrejon.filmlibrary.data.sources

import com.jcastrejon.filmlibrary.domain.models.DomainError
import com.jcastrejon.filmlibrary.domain.models.Movie
import com.jcastrejon.filmlibrary.domain.models.Result

/**
 * Data source interface
 */
internal interface MoviesDataSource {

    /**
     * Get the movies
     */
    fun getMovies(): Result<List<Movie>, DomainError>
}