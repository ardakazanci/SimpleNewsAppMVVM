package com.ardakazanci.simplenewsappmvvm.network

import com.ardakazanci.simplenewsappmvvm.database.DatabaseArticleModel
import com.ardakazanci.simplenewsappmvvm.domain.DomainModel
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class NetworkArticlesContainer(val articles: List<NetworkArticle>)


@JsonClass(generateAdapter = true)
data class NetworkArticle(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

/**
 * Network Response = DomainModel Mapping
 */
fun NetworkArticlesContainer.asDomainModel(): List<DomainModel> {

    return articles.map {
        DomainModel(

            author = it.author,
            title = it.title,
            description = it.description,
            url = it.url,
            urlToImage = it.urlToImage,
            publishedAt = it.publishedAt,
            content = it.content

        )
    }

}

/**
 * Network Response = DatabaseModel Mapping
 */
fun NetworkArticlesContainer.asDatabaseModel(): List<DatabaseArticleModel> {

    return articles.map {

        DatabaseArticleModel(

            author = it.author,
            title = it.title,
            description = it.description,
            urlToImage = it.urlToImage,
            url = it.url,
            publishedAt = it.publishedAt,
            content = it.content

        )

    }

}



