package com.jcastrejon.filmlibrary.data.sources

import com.jcastrejon.filmlibrary.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import com.jcastrejon.filmlibrary.common.API_KEY

/**
 * Interceptor to add the api key
 */
internal class MoviesApiClientInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val url = request.url().newBuilder()
                .addQueryParameter(API_KEY, BuildConfig.API_KEY)
                .build()

        request = request.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}