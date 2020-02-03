package com.ardakazanci.simplenewsappmvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.ardakazanci.simplenewsappmvvm.database.ArticlesDatabase
import com.ardakazanci.simplenewsappmvvm.database.asDomainModel
import com.ardakazanci.simplenewsappmvvm.domain.DomainModel
import com.ardakazanci.simplenewsappmvvm.network.ApiClient
import com.ardakazanci.simplenewsappmvvm.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository will provide us with data from the Database as we have implemented Offline Cache operation
 */
class ArticlesRepository(private val database: ArticlesDatabase) {
    /**
     * database.videoDao.getArticles() return; = > List<DatabaseArticleModel>
     * Since it is LiveData, Mapping to DomainModel is performed using Transformations Map.
     */
    val articles: LiveData<List<DomainModel>> =
        Transformations.map(database.videoDao.getArticles()) {

            it.asDomainModel()

        }


    suspend fun refreshArticles() {

        withContext(Dispatchers.IO) {
            /**
             * The database is kept up-to-date with this technique.
             * Data taken as a list is mapping to the Database Model.
             */
            val articlesList = ApiClient.retrofitClient.getArticles().await()
            database.videoDao.insertAll(articlesList.asDatabaseModel())

        }

    }


}