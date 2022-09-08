package com.dnd.niceteam.ui.mypage.myteample.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseActivity
import com.dnd.niceteam.databinding.ActivityMyTeampleDetailBinding
import com.dnd.niceteam.ui.mypage.myteample.MemberSmallItemDecoration
import com.dnd.niceteam.ui.mypage.myteample.adapter.MemberAdapter
import com.dnd.niceteam.ui.mypage.myteample.adapter.MemberSmallAdapter
import com.dnd.niceteam.ui.mypage.myteample.viewmodel.MyTeampleDetailViewModel
import kotlinx.coroutines.flow.collectLatest

class MyTeampleDetailActivity :
    BaseActivity<ActivityMyTeampleDetailBinding>(R.layout.activity_my_teample_detail) {

    private val viewModel: MyTeampleDetailViewModel by viewModels()
    private val memberSmallAdapter: MemberSmallAdapter by lazy { MemberSmallAdapter() }
    private val memberSmallItemDecoration: MemberSmallItemDecoration by lazy { MemberSmallItemDecoration() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        initView()
        observeData()
    }

    private fun bind() {
        with(binding) {
            memberAdapter = memberSmallAdapter
            memberItemDecoration = memberSmallItemDecoration
        }
    }

    private fun initView() {
        with(binding) {
            initCompleteTeampleView()
            toolbar.clickedNavigationIcon { finish() }
            btnExport.setOnClickListener {
                ExportDialog(viewModel).show(supportFragmentManager, "export_dialog")
            }
        }
    }

    private fun observeData() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                teampleDetail.collectLatest {
                    binding.teampleDetail = it
                    memberSmallAdapter.submitList(it.members)
                }
            }
        }
    }

    private fun initCompleteTeampleView() {
        with(binding) {
            if (viewModel.isCompleteTeample) {
                btnExport.visibility = View.GONE
                tvWriteReview.visibility = View.GONE
                viewTriangle.visibility = View.GONE
                btnComplete.visibility = View.GONE
            } else {
                btnExport.visibility = View.VISIBLE
                tvWriteReview.visibility = View.VISIBLE
                viewTriangle.visibility = View.VISIBLE
                btnComplete.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        const val COMPLETE_TEAMPLE = "complete_teample"
    }
}

// Dummy data class
data class TeampleDetail(
    val id: Int,
    val projectName: String,
    val detail1: String,
    val detail2: String,
    val members: List<Member>,
    val date: String,
    val location: String,
    val firstVote: Boolean
)

data class Member(
    val id: Int,
    val level: Int,
    val nickname: String,
    val title: String,
    val recruiterYn: String = "N"
)