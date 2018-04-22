package com.jcastrejon.filmlibrary.domain.usecases

import com.jcastrejon.filmlibrary.domain.models.DomainError
import com.jcastrejon.filmlibrary.domain.models.Movie
import com.jcastrejon.filmlibrary.domain.models.Result
import com.jcastrejon.filmlibrary.domain.repository.Repository

/**
 * Get movies use case
 */
internal class GetMoviesUseCase(private val moviesRepository: Repository) {

    operator fun invoke(): Result<List<Movie>, DomainError> = moviesRepository.getMovies()
}