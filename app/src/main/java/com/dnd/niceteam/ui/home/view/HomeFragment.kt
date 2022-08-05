package com.dnd.niceteam.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseFragment
import com.dnd.niceteam.databinding.FragmentHomeBinding
import com.dnd.niceteam.ui.home.ProgressTeamplItemDecoration
import com.dnd.niceteam.ui.home.adapter.ProgressTeamplAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val progressTeamplAdapter: ProgressTeamplAdapter by lazy { ProgressTeamplAdapter() }
    private val progressTeamplItemDecoration: ProgressTeamplItemDecoration by lazy { ProgressTeamplItemDecoration() }

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
        progressTeamplAdapter.submitList(arrayListOf(0, 1, 2, 3))
    }

    private fun bind() {
        with(binding) {
            progressTeamplAdapter = this@HomeFragment.progressTeamplAdapter
            progressTeamplItemDecoration = this@HomeFragment.progressTeamplItemDecoration
        }
    }
}