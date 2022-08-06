package com.dnd.niceteam.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseFragment
import com.dnd.niceteam.databinding.FragmentHomeBinding
import com.dnd.niceteam.ui.home.ProgressTeampleItemDecoration
import com.dnd.niceteam.ui.home.adapter.ProgressTeampleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val progressTeampleAdapter: ProgressTeampleAdapter by lazy { ProgressTeampleAdapter() }
    private val progressTeampleItemDecoration: ProgressTeampleItemDecoration by lazy { ProgressTeampleItemDecoration() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        progressTeampleAdapter.submitList(arrayListOf(0, 1, 2, 3))
    }

    private fun bind() {
        with(binding) {
            progressTeampleAdapter = this@HomeFragment.progressTeampleAdapter
            progressTeampleItemDecoration = this@HomeFragment.progressTeampleItemDecoration
        }
    }
}