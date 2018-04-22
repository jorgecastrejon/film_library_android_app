package com.jcastrejon.filmlibrary.data.models

import com.jcastrejon.filmlibrary.domain.models.Movie

/**
 * Map Data Response to the domain model
 */
internal fun convertToDomain(dataResponse: DataResponse): List<Movie> = dataResponse.results.map { convertToDomain(it) }

internal fun convertToDomain(result: Result): Movie =  Movie(result.id,
                                                             result.title,
                                                             result.vote_average,
                                                             result.overview,
                                                             result.release_date,
                                                             result.poster_path,
                                                             result.backdrop_path)
