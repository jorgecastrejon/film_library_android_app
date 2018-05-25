package com.jcastrejon.filmlibrary.data.sources

import okhttp3.OkHttpClient

import com.jcastrejon.filmlibrary.data.models.convertToDomain
import com.jcastrejon.filmlibrary.domain.models.*
import com.jcastrejon.filmlibrary.common.BASE_API_URL

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * Api client to get the data
 */
internal class MoviesApiClient(baseEndpoint: String = BASE_API_URL): MoviesDataSource {

    private val apiService: MoviesApiService

    init {
        val client = OkHttpClient.Builder()
                .addNetworkInterceptor(MoviesApiClientInterceptor())
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(baseEndpoint)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        apiService = retrofit.create(MoviesApiService::class.java)
    }

    override fun getMovies(): Result<List<Movie>, DomainError> = try {
        val response = apiService.getMovies().execute()

        when {
            response.isSuccessful && response.body() != null -> Success(convertToDomain(response.body()!!))
            else -> Error(UnknownError)
        }

    } catch (e: IOException) {
        Error(InternetError)
    }

    override fun getMovieById(movieId: Int): Result<Movie, DomainError> = try {
        val response = apiService.getMovieById(movieId.toString()).execute()

        when {
            response.isSuccessful && response.body() != null -> Success(convertToDomain(response.body()!!))
            else -> Error(UnknownError)
        }
    } catch (e: IOException) {
        Error(InternetError)
    }
}