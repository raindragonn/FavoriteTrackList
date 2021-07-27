package com.raindragonn.favoritetracklist.ui.favorite

import android.os.Bundle
import android.view.View
import com.raindragonn.favoritetracklist.R
import com.raindragonn.favoritetracklist.databinding.FragmentFavoriteListBinding
import com.raindragonn.favoritetracklist.ui.adapter.FavoriteListAdapter
import com.raindragonn.favoritetracklist.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment :
    BaseFragment<FragmentFavoriteListBinding>(R.layout.fragment_favorite_list) {
    companion object {
        const val TAG = "FavoriteFragment"
    }

    private val viewModel: FavoriteViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
    }

    override fun initViews() = with(binding) {
        rvFavorite.adapter = FavoriteListAdapter(viewModel.onFavoriteClick)
        rvFavorite.itemAnimator = null
    }
}