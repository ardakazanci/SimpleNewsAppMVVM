package com.ardakazanci.simplenewsappmvvm.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("isNetworkError", "articles")
fun hideIfNetworkError(view: View, isNetWorkError: Boolean, articles: Any?) {

    /**
     * Item - ViewHolder control
     */
    view.visibility = if (articles != null) View.GONE else View.VISIBLE

    /**
     * ProgressBar control
     */
    if (isNetWorkError) {
        view.visibility = View.GONE
    }
}


@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}