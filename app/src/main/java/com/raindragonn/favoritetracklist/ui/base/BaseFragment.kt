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
                lifecycleOwner = viewLifecycleOwner
            }
        }.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}