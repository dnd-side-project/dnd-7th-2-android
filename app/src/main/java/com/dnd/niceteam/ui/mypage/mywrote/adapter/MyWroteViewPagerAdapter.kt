package com.dnd.niceteam.ui.mypage.mywrote.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dnd.niceteam.ui.mypage.mywrote.view.MyCommentFragment
import com.dnd.niceteam.ui.mypage.mywrote.view.MyRecruitmentFragment

class MyWroteViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return MyRecruitmentFragment()
            1 -> return MyCommentFragment()
        }
        return MyRecruitmentFragment()
    }
}