package com.ardakazanci.simplenewsappmvvm.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ardakazanci.simplenewsappmvvm.R
import com.ardakazanci.simplenewsappmvvm.databinding.ArticleListFragmentBinding
import com.ardakazanci.simplenewsappmvvm.domain.DomainModel
import com.ardakazanci.simplenewsappmvvm.viewmodels.ArticleClick
import com.ardakazanci.simplenewsappmvvm.viewmodels.ArticleListViewModel
import com.ardakazanci.simplenewsappmvvm.viewmodels.ArticlesAdapter


class ArticleListFragment : Fragment() {


    private val viewModel: ArticleListViewModel by lazy {

        val activity = requireNotNull(this.activity) {

            "You can only access the viewModel after onActivityCreated()"
        }

        ViewModelProviders.of(this, ArticleListViewModel.Factory(activity.application))
            .get(ArticleListViewModel::class.java)

    }

    private var viewModelAdapter: ArticlesAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<ArticleListFragmentBinding>(
            inflater,
            R.layout.article_list_fragment,
            container,
            false
        )



        binding.lifecycleOwner = viewLifecycleOwner



        binding.viewModel = viewModel

        viewModelAdapter = ArticlesAdapter(ArticleClick {
            Log.e("ArticlesListFg", "VideoClick")
        })

        binding.root.findViewById<RecyclerView>(R.id.rv_article_list).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        // Observer for the network error.
        viewModel.eventNetworkError.observe(
            viewLifecycleOwner,
            Observer<Boolean> { isNetworkError ->
                if (isNetworkError) onNetworkError()
            })


        return binding.root
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.articles.observe(viewLifecycleOwner, Observer<List<DomainModel>> { news ->
            news?.apply {
                viewModelAdapter?.articles = news
            }
        })
    }

}
