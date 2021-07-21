package com.raindragonn.favoritetracklist.ui.track

import android.os.Bundle
import android.view.View
import com.raindragonn.favoritetracklist.R
import com.raindragonn.favoritetracklist.databinding.FragmentTrackListBinding
import com.raindragonn.favoritetracklist.ui.adapter.TrackListAdapter
import com.raindragonn.favoritetracklist.ui.base.BaseFragment
import com.raindragonn.favoritetracklist.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TrackFragment : BaseFragment<FragmentTrackListBinding>(R.layout.fragment_track_list) {
    companion object {
        const val TAG = "TrackFragment"
    }

    // 메인 엑티비티의 뷰모델을 공유하여 사용합니다.
    // koin의 sharedViewModel 사용함으로 새로운 뷰모델이 아닌 Activity 에서 생성한 뷰모델을 사용합니다.
    private val viewModel: MainViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            vm = viewModel
        }
        initRecyclerView()
    }

    private fun initRecyclerView() = with(binding) {
        rvTrack.adapter = TrackListAdapter()
        rvTrack.itemAnimator = null
    }
}