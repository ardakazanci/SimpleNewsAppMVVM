package com.ardakazanci.simplenewsappmvvm.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ardakazanci.simplenewsappmvvm.domain.DomainModel

@Entity
data class DatabaseArticleModel(
    val author: String,
    val title: String,
    val description: String,
    @PrimaryKey
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

/**
 * Database Objects = DomainModel Mapping
 */
fun List<DatabaseArticleModel>.asDomainModel(): List<DomainModel> {

    return map {

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



