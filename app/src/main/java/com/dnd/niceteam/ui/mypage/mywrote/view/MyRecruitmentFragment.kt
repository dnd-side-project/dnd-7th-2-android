package com.dnd.niceteam.ui.mypage.mywrote.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseFragment
import com.dnd.niceteam.databinding.FragmentMyRecruitmentBinding
import com.dnd.niceteam.ui.common.TeamGooCalendarDialog
import com.dnd.niceteam.ui.common.TeamGooDialog2
import com.dnd.niceteam.ui.common.teamGooToastMessage
import com.dnd.niceteam.ui.mypage.mywrote.MyRecruitmentItemDecoration
import com.dnd.niceteam.ui.mypage.mywrote.adapter.MyRecruitmentAdapter
import com.dnd.niceteam.ui.mypage.mywrote.viewmodel.MyWroteViewModel
import com.prolificinteractive.materialcalendarview.MaterialCalendarView.SELECTION_MODE_SINGLE
import kotlinx.coroutines.flow.collectLatest

class MyRecruitmentFragment :
    BaseFragment<FragmentMyRecruitmentBinding>(R.layout.fragment_my_recruitment) {

    private val viewModel: MyWroteViewModel by activityViewModels()
    private val adapter: MyRecruitmentAdapter by lazy {
        MyRecruitmentAdapter(
            {
                TeamGooCalendarDialog(
                    title = "끌어 올릴 모집글의 마감기한을 알려주세요.",
                    checkBoxTitle = "기간 제한 없어요.",
                    selectionMode = SELECTION_MODE_SINGLE
                ) {
                    requireContext().teamGooToastMessage("끌어올리기가 완료되었어요!")!!.show()
                }.show(parentFragmentManager, "calendar_dialog")
            },
            {
                TeamGooDialog2(
                    message = "모집완료로 변경해\n지원자를 그만 받을까요?",
                    firstButtonText = "취소하기",
                    secondButtonText = "모집완료",
                    {}
                ) {}.show(parentFragmentManager, "msg_dialog")
            }
        )
    }
    private val itemDecoration: MyRecruitmentItemDecoration by lazy { MyRecruitmentItemDecoration() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        initView()
        observeData()
    }

    private fun bind() {
        with(binding) {
            viewModel = this@MyRecruitmentFragment.viewModel
            adapter = this@MyRecruitmentFragment.adapter
            itemDecoration = this@MyRecruitmentFragment.itemDecoration
        }
    }

    private fun initView() {
        with(binding) {
            chipGroup.apply {
                setOnCheckedStateChangeListener { _, ids ->
                    when (ids[0]) {
                        R.id.chip_all ->
                            this@MyRecruitmentFragment.viewModel.updateRecruitmentStatus(Recruitment.ALL)
                        R.id.chip_recruitment ->
                            this@MyRecruitmentFragment.viewModel.updateRecruitmentStatus(Recruitment.ONGOING)
                        R.id.chip_complete_recruitment ->
                            this@MyRecruitmentFragment.viewModel.updateRecruitmentStatus(Recruitment.COMPLETE)
                        R.id.chip_fail_recruitment ->
                            this@MyRecruitmentFragment.viewModel.updateRecruitmentStatus(Recruitment.FAIL)
                    }
                }
                check(R.id.chip_all)
            }
        }
    }

    private fun observeData() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                recruitmentStatus.collectLatest { status ->
                    val list = updateRecruitmentList(status)
                    adapter.submitList(list)
                }
            }
            lifecycleScope.launchWhenStarted {
                curRecruitments.collectLatest {
                    binding.layoutEmpty.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
                }
            }
        }
    }

    companion object {
        private const val TAG = "RecruitmentTabFragment"
    }
}