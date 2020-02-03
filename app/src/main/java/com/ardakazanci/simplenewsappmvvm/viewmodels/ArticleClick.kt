package com.ardakazanci.simplenewsappmvvm.viewmodels

import com.ardakazanci.simplenewsappmvvm.domain.DomainModel

class ArticleClick(val block: (DomainModel) -> Unit) {

    fun onClick(video: DomainModel) = block(video)

}