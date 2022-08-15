package com.dnd.niceteam.ui.home.bookmark.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseActivity
import com.dnd.niceteam.databinding.ActivityBookmarkBinding
import com.dnd.niceteam.ui.home.bookmark.BookmarkItemDecoration
import com.dnd.niceteam.ui.home.bookmark.adapter.BookmarkAdapter
import com.dnd.niceteam.ui.home.bookmark.viewmodel.BookmarkViewModel
import com.dnd.niceteam.ui.home.bookmark.viewmodel.BookmarkViewModel.Companion.MY_LECTURE
import com.dnd.niceteam.ui.home.bookmark.viewmodel.BookmarkViewModel.Companion.SIDE
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookmarkActivity : BaseActivity<ActivityBookmarkBinding>(R.layout.activity_bookmark) {

    private val bookmarkViewModel: BookmarkViewModel by viewModels()
    private val bookmarkAdapter: BookmarkAdapter by lazy { BookmarkAdapter() }
    private val bookmarkItemDecoration: BookmarkItemDecoration by lazy { BookmarkItemDecoration() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        initView()
        observeData()
    }

    private fun bind() {
        with(binding) {
            bookmarkViewModel = this@BookmarkActivity.bookmarkViewModel
            bookmarkAdapter = this@BookmarkActivity.bookmarkAdapter
            bookmarkItemDecoration = this@BookmarkActivity.bookmarkItemDecoration
        }
    }

    private fun initView() {
        with(binding) {
            toolbar.clickedNavigationIcon {
                finish()
            }
            layoutTab.apply {
                getTabAt(MY_LECTURE)?.text = "내 강의 2"
                getTabAt(SIDE)?.text = "사이드 0"
                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        val position = tab?.position ?: return
                        this@BookmarkActivity.bookmarkViewModel.setBookmarkType(position)
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {}

                    override fun onTabReselected(tab: TabLayout.Tab?) {}
                })
            }
        }
    }

    private fun observeData() {
        with(bookmarkViewModel) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    bookmarkType.collectLatest { type ->
                        updateBookmarkList(type)
                    }
                }
            }
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    bookmarkList.collectLatest { list ->
                        val type = bookmarkType.value
                        val tab = binding.layoutTab.getTabAt(type)
                        tab?.text =
                            if (type == MY_LECTURE) "내 강의 ${list.size}"
                            else "사이드 ${list.size}"
                        bookmarkAdapter.submitList(list)
                    }
                }
            }
        }
    }
}