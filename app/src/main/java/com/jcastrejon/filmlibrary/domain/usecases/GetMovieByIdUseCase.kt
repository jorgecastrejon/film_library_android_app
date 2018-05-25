package com.jcastrejon.filmlibrary.domain.usecases

import com.jcastrejon.filmlibrary.domain.models.DomainError
import com.jcastrejon.filmlibrary.domain.models.Movie
import com.jcastrejon.filmlibrary.domain.models.Result
import com.jcastrejon.filmlibrary.domain.repository.Repository

/**
 * Get a movie by its id
 */
internal class GetMovieByIdUseCase(private val moviesRepository: Repository) {

    operator fun invoke(movieId: Int): Result<Movie, DomainError> = moviesRepository.getMovieById(movieId)
}