package com.ardakazanci.simplenewsappmvvm.viewmodels

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ardakazanci.simplenewsappmvvm.R
import com.ardakazanci.simplenewsappmvvm.databinding.ItemArticleBinding
import com.ardakazanci.simplenewsappmvvm.domain.DomainModel

class ArticlesAdapter(val callback: ArticleClick) : RecyclerView.Adapter<ArticlesViewHolder>() {

    var articles: List<DomainModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {

        val withDataBinding: ItemArticleBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArticlesViewHolder.LAYOUT,
            parent,
            false
        )
        return ArticlesViewHolder(withDataBinding)

    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.articlesModel = articles[position]
            it.articleClick = callback
        }
    }


}

class ArticlesViewHolder(val viewDataBinding: ItemArticleBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.item_article
    }
}