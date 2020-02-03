package com.ardakazanci.simplenewsappmvvm.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.ardakazanci.simplenewsappmvvm.database.getDatabase
import com.ardakazanci.simplenewsappmvvm.repository.ArticlesRepository
import kotlinx.coroutines.*
import java.io.IOException

class ArticleListViewModel(private val app: Application) : AndroidViewModel(app) {

    /**
     * Repository object
     */
    private val articleRepository: ArticlesRepository = ArticlesRepository(getDatabase(app))

    /**
     * Repository with articles getter
     */
    val articles = articleRepository.articles

    /**
     * Coroutines Job
     */
    private val viewModelJob = SupervisorJob()

    /**
     * Coroutines Scope for Main Thread
     */
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * Network Error ?
     */
    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    /**
     * Network Error Message ?
     */
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {

        refreshDataFromRepository()

    }

    /**
     * Get News with Articles
     */
    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                articleRepository.refreshArticles()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (networkError: IOException) {

                if (articles.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }

    /**
     * ViewModel cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }


    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArticleListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ArticleListViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }


}
