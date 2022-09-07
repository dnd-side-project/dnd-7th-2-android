package com.dnd.niceteam.ui.mypage.mywrote.view

import android.os.Bundle
import androidx.activity.viewModels
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseActivity
import com.dnd.niceteam.databinding.ActivityMyWroteBinding
import com.dnd.niceteam.ui.mypage.mywrote.adapter.MyWroteViewPagerAdapter
import com.dnd.niceteam.ui.mypage.mywrote.viewmodel.MyWroteViewModel
import com.google.android.material.tabs.TabLayoutMediator

class MyWroteActivity : BaseActivity<ActivityMyWroteBinding>(R.layout.activity_my_wrote) {

    private val myWroteViewModel: MyWroteViewModel by viewModels()
    private val myWroteViewPagerAdapter: MyWroteViewPagerAdapter by lazy {
        MyWroteViewPagerAdapter(supportFragmentManager, lifecycle)
    }
    private val tabTitles = arrayOf("모집글", "댓글")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        with(binding) {
            toolbar.clickedNavigationIcon {
                finish()
            }
            viewPager.adapter = myWroteViewPagerAdapter
            TabLayoutMediator(layoutTab, viewPager) { tab, position ->
                tab.text = tabTitles[position]
            }.attach()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}

enum class Recruitment {
    ALL,
    ONGOING,
    COMPLETE,
    FAIL
}

fun getRecruitmentStatus(recruitment: Recruitment): String {
    return when(recruitment) {
        Recruitment.ALL -> "전체"
        Recruitment.ONGOING -> "모집중"
        Recruitment.COMPLETE -> "모집완료"
        Recruitment.FAIL -> "모집실패"
    }
}