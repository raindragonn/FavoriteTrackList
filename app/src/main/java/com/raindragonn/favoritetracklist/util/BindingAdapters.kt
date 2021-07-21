package com.raindragonn.favoritetracklist.util

import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.raindragonn.favoritetracklist.data.model.TrackItem
import com.raindragonn.favoritetracklist.ui.adapter.TrackListAdapter

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
    (view.adapter as? TrackListAdapter)?.apply {
        submitList(items)
    }
}

@BindingAdapter("setOnclick")
fun setFavoriteClick(view: RecyclerView, click: ((TrackItem) -> Unit)?) {
    (view.adapter as? TrackListAdapter)?.apply {
        favoriteClickListener = click
    }
}

@BindingAdapter("setLoadMoreListener")
fun setLoadMoreListener(view: RecyclerView, loadMore: (() -> Unit)?) {
    (view.adapter as? TrackListAdapter)?.apply {
        loadMoreListener = loadMore
    }
}

@BindingAdapter("setRefreshClick")
fun setRefreshClickListener(view: View, refresh: (() -> Unit)?) {
    view.setOnClickListener {
        refresh?.invoke()
    }
}
