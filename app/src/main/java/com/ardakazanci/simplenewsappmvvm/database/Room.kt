package com.ardakazanci.simplenewsappmvvm.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArticleDao {
    @Query("SELECT * FROM databasearticlemodel")
    fun getArticles(): LiveData<List<DatabaseArticleModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<DatabaseArticleModel>)
}

@Database(entities = [DatabaseArticleModel::class], version = 1)
abstract class ArticlesDatabase : RoomDatabase() {
    abstract val videoDao: ArticleDao
}


private lateinit var INSTANCE: ArticlesDatabase

fun getDatabase(context: Context): ArticlesDatabase {
    synchronized(ArticlesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                ArticlesDatabase::class.java,
                "articles"
            ).build()
        }
    }
    return INSTANCE
}

