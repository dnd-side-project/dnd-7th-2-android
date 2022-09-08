package com.dnd.niceteam.ui.mypage.myteample.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseActivity
import com.dnd.niceteam.databinding.ActivityMyTeampleBinding
import com.dnd.niceteam.ui.mypage.myteample.MyTeampleItemDecoration
import com.dnd.niceteam.ui.mypage.myteample.adapter.MyTeampleAdapter
import com.dnd.niceteam.ui.mypage.myteample.view.MyTeampleDetailActivity.Companion.COMPLETE_TEAMPLE
import com.dnd.niceteam.ui.mypage.myteample.viewmodel.MyTeampleViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.collectLatest

class MyTeampleActivity : BaseActivity<ActivityMyTeampleBinding>(R.layout.activity_my_teample) {

    private val viewModel: MyTeampleViewModel by viewModels()
    private val adapter: MyTeampleAdapter by lazy {
        MyTeampleAdapter(
            viewModel = viewModel,
            clickedContainer = {
                val intent = Intent(this, MyTeampleDetailActivity::class.java).apply {
                    putExtra(COMPLETE_TEAMPLE, viewModel.tabPosition.value == 1)
                }
                startActivity(intent)
            },
            clickedReview = {
                startActivity(Intent(this, ReviewActivity::class.java))
            }
        )
    }
    private val itemDecoration: MyTeampleItemDecoration by lazy { MyTeampleItemDecoration() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        initView()
        observeData()
    }

    private fun bind() {
        with(binding) {
            viewModel = this@MyTeampleActivity.viewModel
            adapter = this@MyTeampleActivity.adapter
            itemDecoration = this@MyTeampleActivity.itemDecoration
        }
    }

    private fun initView() {
        with(binding) {
            toolbar.clickedNavigationIcon { finish() }
            layoutTab.apply {
                addTab(newTab().apply { text = "진행중" })
                addTab(newTab().apply { text = "완료" })
                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        val position = tab?.position ?: return
                        this@MyTeampleActivity.viewModel.setTabPosition(position)
                        this@MyTeampleActivity.adapter.notifyDataSetChanged()
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {}

                    override fun onTabReselected(tab: TabLayout.Tab?) {}
                })
            }
        }
    }

    private fun observeData() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                teampleList.collectLatest { list ->
                    adapter.submitList(list)
                }
            }
        }
    }
}