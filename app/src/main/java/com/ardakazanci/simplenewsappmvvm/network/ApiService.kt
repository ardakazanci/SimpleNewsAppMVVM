package com.ardakazanci.simplenewsappmvvm.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET


/**
 * Endpoints to which retrofit requests will be discarded are determined. It is deferred
 * because retrofit manages many thread operations. As Await, we will wait for the relevant result.
 */
interface ApiService {

    @GET("/v2/top-headlines?country=us")
    fun getArticles(): Deferred<NetworkArticlesContainer>

}



