package com.raindragonn.favoritetracklist.ui.track

import android.os.Bundle
import android.view.View
import androidx.paging.LoadState
import com.raindragonn.favoritetracklist.R
import com.raindragonn.favoritetracklist.databinding.FragmentTrackListBinding
import com.raindragonn.favoritetracklist.ui.adapter.TrackPagingAdapter
import com.raindragonn.favoritetracklist.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrackFragment : BaseFragment<FragmentTrackListBinding>(R.layout.fragment_track_list) {
    companion object {
        const val TAG = "TrackFragment"
    }

    private val viewModel: TrackViewModel by viewModel()
    private val adapter: TrackPagingAdapter by lazy { TrackPagingAdapter(viewModel.onFavoriteClick) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initViews()
        onObserve()
    }

    override fun initViews() = with(binding) {

        rvTrack.adapter = adapter.apply {
            /**
             * LoadState 객체를 통해 상태를 알아온다.
             */
            addLoadStateListener { loadState ->
                val isLoading = loadState.source.refresh is LoadState.Loading
                val isError = loadState.source.refresh is LoadState.Error

                viewModel.changeStateLoading(isLoading)
                viewModel.changeStateError(isError)
            }
        }
        rvTrack.itemAnimator = null
        tvError.setOnClickListener { adapter.retry() }
    }


    private fun onObserve() = with(viewModel) {
        trackPagingData.observe(viewLifecycleOwner) { pagingData ->
            adapter.submitData(lifecycle, pagingData)
        }

        favoriteList.observe(viewLifecycleOwner) { favoriteList ->
            matchedTrackListByFavorite(favoriteList)
        }
    }
}