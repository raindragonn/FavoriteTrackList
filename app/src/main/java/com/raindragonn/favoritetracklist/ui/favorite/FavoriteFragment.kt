package com.raindragonn.favoritetracklist.ui.favorite

import android.os.Bundle
import android.view.View
import com.raindragonn.favoritetracklist.R
import com.raindragonn.favoritetracklist.databinding.FragmentFavoriteListBinding
import com.raindragonn.favoritetracklist.ui.adapter.TrackListAdapter
import com.raindragonn.favoritetracklist.ui.base.BaseFragment
import com.raindragonn.favoritetracklist.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FavoriteFragment :
    BaseFragment<FragmentFavoriteListBinding>(R.layout.fragment_favorite_list) {

    private val viewModel: MainViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        initRecyclerView()
    }

    private fun initRecyclerView() = with(binding) {
        rvFavorite.adapter = TrackListAdapter()
    }
}