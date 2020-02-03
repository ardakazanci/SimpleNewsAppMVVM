package com.ardakazanci.simplenewsappmvvm.domain


/**
 * Model structure I will use in the whole application
 */
data class DomainModel(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)