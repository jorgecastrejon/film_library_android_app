package com.jcastrejon.filmlibrary.domain.models

internal data class Movie(val id: Int,
                          val title: String,
                          val rate: Number,
                          val description: String,
                          val date: String,
                          val poster: String,
                          val backdrop: String)

internal sealed class DomainError
internal object InternetError: DomainError()
internal object UnknownError: DomainError()

internal sealed class Result<out S, out E>
internal data class Success<out S>(val value: S) : Result<S, Nothing>()
internal data class Error<out E>(val value: E) : Result<Nothing, E>()