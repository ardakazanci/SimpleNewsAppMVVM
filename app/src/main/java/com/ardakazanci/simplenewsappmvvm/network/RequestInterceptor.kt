package com.ardakazanci.simplenewsappmvvm.network

import com.ardakazanci.simplenewsappmvvm.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Allows apiKey to be appended to each url request.
 */
class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apiKey", Constants.API_KEY)
            .build()

        val request = originalRequest.newBuilder().url(url).build()
        return chain.proceed(request)

    }


}