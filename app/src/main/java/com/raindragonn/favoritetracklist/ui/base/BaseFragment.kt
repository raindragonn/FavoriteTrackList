package com.raindragonn.favoritetracklist.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VB : ViewDataBinding>(
    @LayoutRes
    private val layoutId: Int
) : Fragment() {
    protected var _binding: VB? = null
    val binding: VB
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<VB>(
            inflater,
            layoutId,
            container,
            false
        ).also {
            _binding = it.apply {
                // Fragment 의 라이프 사이클이 Fragment View 보다 길기 때문에
                // 중복 호출을 피하기 위해 viewLifecycleOwner 이용
                lifecycleOwner = viewLifecycleOwner
            }
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    abstract fun initViews()

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}