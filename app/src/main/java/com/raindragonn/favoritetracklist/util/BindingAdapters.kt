package com.raindragonn.favoritetracklist.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.raindragonn.favoritetracklist.data.model.TrackItem
import com.raindragonn.favoritetracklist.ui.adapter.FavoriteListAdapter

@BindingAdapter("loadUrl")
fun bindUrlImage(view: ImageView, url: String) {
    Glide.with(view)
        .load(url)
        .thumbnail(0.1f)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)
}

@BindingAdapter("items")
fun setItems(view: RecyclerView, items: List<TrackItem>?) {
    (view.adapter as? FavoriteListAdapter)?.apply {
        submitList(items)
    }
}

