package com.dnd.niceteam.ui.mypage.myteample.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dnd.niceteam.ui.mypage.myteample.view.ReviewFragment
import com.dnd.niceteam.ui.mypage.myteample.viewmodel.ReviewViewModel

class ReviewAdapter(
    private val viewModel: ReviewViewModel,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val clickedSkip: (position: Int) -> Unit
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = viewModel.memberList.value.size

    override fun createFragment(position: Int): Fragment {
        return ReviewFragment(viewModel, position, clickedSkip)
    }
}