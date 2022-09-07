package com.dnd.niceteam.ui.mypage.profile.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.children
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseActivity
import com.dnd.niceteam.databinding.ActivityEditProfileBinding
import com.dnd.niceteam.ui.mypage.profile.view.EditAppealActivity.Companion.APPEAL
import com.dnd.niceteam.ui.mypage.profile.view.EditAppealActivity.Companion.LINK
import com.dnd.niceteam.ui.mypage.profile.view.EditMyTitleDialog.Companion.ADJECTIVE
import com.dnd.niceteam.ui.mypage.profile.view.EditMyTitleDialog.Companion.NOUN
import com.dnd.niceteam.ui.mypage.profile.view.EditNicknameActivity.Companion.NICKNAME
import com.dnd.niceteam.ui.mypage.profile.viewmodel.EditProfileViewModel

class EditProfileActivity :
    BaseActivity<ActivityEditProfileBinding>(R.layout.activity_edit_profile) {

    private val editProfileViewModel: EditProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        initView()
    }

    private fun bind() {
        with(binding) {
            viewModel = editProfileViewModel
        }
    }

    private fun initView() {
        with(binding) {
            toolbar.clickedNavigationIcon { finish() }
            tvNickname.setOnClickListener { goToEditNicknameActivity() }
            tvMyTitleAdjective.setOnClickListener { startEditMyTitleDialog(ADJECTIVE) }
            tvMyTitleNoun.setOnClickListener { startEditMyTitleDialog(NOUN) }
            chipGroupInterest.apply {
                setOnCheckedStateChangeListener { _, checkedIds ->
                    children.forEach {
                        it.isClickable = !(checkedIds.size >= 3 && !checkedIds.contains(it.id))
                    }
                }
            }
            tvAppeal.setOnClickListener { goToEditAppealActivity() }
            tvLink.setOnClickListener { goToEditAppealActivity() }
        }
    }

    private fun goToEditNicknameActivity() {
        val intent =
            Intent(this@EditProfileActivity, EditNicknameActivity::class.java).apply {
                putExtra(NICKNAME, binding.tvNickname.text)
            }
        startActivity(intent)
    }

    private fun goToEditAppealActivity() {
        val intent =
            Intent(this@EditProfileActivity, EditAppealActivity::class.java).apply {
                putExtra(APPEAL, binding.tvAppeal.text)
                putExtra(LINK, binding.tvLink.text)
            }
        startActivity(intent)
    }

    private fun startEditMyTitleDialog(type: String) {
        EditMyTitleDialog(type, editProfileViewModel).show(
            supportFragmentManager,
            EDIT_TITLE_DIALOG
        )
    }

    companion object {
        private const val EDIT_TITLE_DIALOG = "edit_title_dialog"
    }
}