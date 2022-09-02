package com.dnd.niceteam.ui.mypage.profile.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseActivity
import com.dnd.niceteam.databinding.ActivityProfileBinding
import com.dnd.niceteam.ui.mypage.profile.adapter.ProfileRankAdapter
import com.dnd.niceteam.ui.mypage.profile.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProfileActivity : BaseActivity<ActivityProfileBinding>(R.layout.activity_profile) {

    private val profileViewModel: ProfileViewModel by viewModels()
    private val profileRankAdapter: ProfileRankAdapter by lazy {
        ProfileRankAdapter(profileViewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        initView()
        observeData()
    }

    private fun bind() {
        with(binding) {
            profileRankAdapter = this@ProfileActivity.profileRankAdapter
        }
    }

    private fun initView() {
        with(binding) {
            ivNavigationIcon.setOnClickListener { finish() }
            btnEditProfile.setOnClickListener {
                startActivity(Intent(this@ProfileActivity, EditProfileActivity::class.java))
            }
            btnAllRank.setOnClickListener {
                it.visibility = View.INVISIBLE
                profileViewModel.showAllProfileRank()
                this@ProfileActivity.profileRankAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun observeData() {
        with(profileViewModel) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    profileRankList.collectLatest {
                        profileRankAdapter.submitList(it)
                    }
                }
            }
        }
    }
}