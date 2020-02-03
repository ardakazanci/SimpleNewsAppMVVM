package com.ardakazanci.simplenewsappmvvm.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {

    @GET("/top-headlines?country=tr")
    fun getArticles(): Deferred<NetworkArticlesContainer>

}



