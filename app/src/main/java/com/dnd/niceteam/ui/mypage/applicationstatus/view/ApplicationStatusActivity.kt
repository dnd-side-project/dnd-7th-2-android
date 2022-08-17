package com.dnd.niceteam.ui.mypage.applicationstatus.view

import android.os.Bundle
import androidx.activity.viewModels
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseActivity
import com.dnd.niceteam.databinding.ActivityApplicationStatusBinding
import com.dnd.niceteam.ui.mypage.applicationstatus.ApplicationStatusItemDecoration
import com.dnd.niceteam.ui.mypage.applicationstatus.adapter.ApplicationStatusAdapter
import com.dnd.niceteam.ui.mypage.applicationstatus.viewmodel.ApplicationStatusViewModel

class ApplicationStatusActivity :
    BaseActivity<ActivityApplicationStatusBinding>(R.layout.activity_application_status) {

    private val viewModel: ApplicationStatusViewModel by viewModels()
    private val adapter: ApplicationStatusAdapter by lazy { ApplicationStatusAdapter() }
    private val itemDecoration: ApplicationStatusItemDecoration by lazy { ApplicationStatusItemDecoration() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        initView()
    }

    private fun bind() {
        with(binding) {
            viewModel = this@ApplicationStatusActivity.viewModel
            adapter = this@ApplicationStatusActivity.adapter
            itemDecoration = this@ApplicationStatusActivity.itemDecoration
        }
    }

    private fun initView() {
        with(binding) {
            toolbar.clickedNavigationIcon {
                finish()
            }
            chipGroup.apply {
                setOnCheckedStateChangeListener { _, ids ->
                    when (ids[0]) {
                        R.id.chip_all -> updateStatusList(STATUS_ALL)
                        R.id.chip_apply -> updateStatusList(STATUS_APPLY)
                        R.id.chip_accept -> updateStatusList(STATUS_ACCEPT)
                        R.id.chip_end_recruitment -> updateStatusList(STATUS_END_RECRUITMENT)
                    }
                }
                check(R.id.chip_all)
            }
        }
    }

    private fun updateStatusList(type: String) = adapter.submitList(viewModel.listFiltering(type))

    companion object {
        const val STATUS_ALL = "전체"
        const val STATUS_APPLY = "지원중"
        const val STATUS_ACCEPT = "수락"
        const val STATUS_END_RECRUITMENT = "모집종료"
    }
}