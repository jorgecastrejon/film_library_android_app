package com.jcastrejon.filmlibrary.data.sources

import com.jcastrejon.filmlibrary.data.models.DataResponse
import retrofit2.Call
import retrofit2.http.GET

internal interface MoviesApiService {

    @GET(value = "3/discover/movie?sort_by=popularity.desc")
    fun getMovies(): Call<DataResponse>
}
