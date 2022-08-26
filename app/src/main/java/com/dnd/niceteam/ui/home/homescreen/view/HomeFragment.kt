package com.dnd.niceteam.ui.home.homescreen.view

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseFragment
import com.dnd.niceteam.databinding.FragmentHomeBinding
import com.dnd.niceteam.ui.home.alarm.view.AlarmActivity
import com.dnd.niceteam.ui.home.bookmark.view.BookmarkActivity
import com.dnd.niceteam.ui.home.homescreen.ProgressTeampleItemDecoration
import com.dnd.niceteam.ui.home.homescreen.RecommendTeampleItemDecoration
import com.dnd.niceteam.ui.home.homescreen.adapter.ProgressTeampleAdapter
import com.dnd.niceteam.ui.home.homescreen.adapter.RecommendTeampleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val progressTeampleAdapter: ProgressTeampleAdapter by lazy { ProgressTeampleAdapter() }
    private val progressTeampleItemDecoration: ProgressTeampleItemDecoration by lazy { ProgressTeampleItemDecoration() }
    private val recommendTeampleAdapter: RecommendTeampleAdapter by lazy { RecommendTeampleAdapter() }
    private val recommendTeampleItemDecoration: RecommendTeampleItemDecoration by lazy { RecommendTeampleItemDecoration() }

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
        initView()
        // Dummy
        progressTeampleAdapter.submitList(arrayListOf(0, 1, 2, 3))
        recommendTeampleAdapter.submitList(arrayListOf(0, 1, 2, 3))
    }

    private fun initView() {
        with(binding) {
            toolbar.apply {
                clickedFirstAction {
                    startActivity(
                        Intent(
                            requireContext(),
                            BookmarkActivity::class.java
                        )
                    )
                }
                clickedSecondAction {
                    startActivity(
                        Intent(
                            requireContext(),
                            AlarmActivity::class.java
                        )
                    )
                }
            }
            layoutHomeTopBanner.tvTopBanner.text = getTopBannerText("닉네임")

        }
    }

    private fun bind() {
        with(binding) {
            progressTeampleAdapter = this@HomeFragment.progressTeampleAdapter
            progressTeampleItemDecoration = this@HomeFragment.progressTeampleItemDecoration
            recommendTeampleAdapter = this@HomeFragment.recommendTeampleAdapter
            recommendTeampleItemDecoration = this@HomeFragment.recommendTeampleItemDecoration
        }
    }

    private fun getTopBannerText(nickName: String): Spanned {
        val htmlText =
            "<font>${nickName}님과<br></font><font><b>딱 맞는 팀원을<br></b></font><font>구해봐요!</font>"
        return Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT)
    }
}