package com.dnd.niceteam.ui.mypage.profile.view

import android.os.Bundle
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseActivity
import com.dnd.niceteam.databinding.ActivityEditAppealBinding
import com.dnd.niceteam.ui.common.TeamGooDialog

class EditAppealActivity : BaseActivity<ActivityEditAppealBinding>(R.layout.activity_edit_appeal) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        processIntent()
        initView()
    }

    private fun processIntent() {
        intent?.let {
            binding.etAppeal.setText(it.getStringExtra(APPEAL) ?: "")
            binding.etLink.setText(it.getStringExtra(LINK) ?: "")
        }
    }

    private fun initView() {
        with(binding) {
            toolbar.clickedNavigationIcon { showFinishDialog() }
        }
    }

    private fun showFinishDialog() {
        TeamGooDialog(
            "작성 중인 정보가 모두 사라져요.\n그래도 페이지에서 나갈까요?",
            "취소",
            "나가기",
            {}
        ) {
            finish()
        }.show(supportFragmentManager, FINISH_DIALOG)
    }

    override fun onBackPressed() {
        showFinishDialog()
    }

    companion object {
        const val APPEAL = "appeal"
        const val LINK = "link"
        const val FINISH_DIALOG = "finish_dialog"
    }
}