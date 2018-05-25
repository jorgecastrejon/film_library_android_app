package com.jcastrejon.filmlibrary.data.sources

import com.jcastrejon.filmlibrary.data.models.DataResponse
import com.jcastrejon.filmlibrary.data.models.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface MoviesApiService {

    @GET(value = "3/discover/movie?sort_by=popularity.desc")
    fun getMovies(): Call<DataResponse>

    @GET(value = "3/movie/{movieId}?")
    fun getMovieById(@Path("movieId") movieId: String): Call<Result>
}
