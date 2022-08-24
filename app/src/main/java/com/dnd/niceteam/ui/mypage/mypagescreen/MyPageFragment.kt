package com.dnd.niceteam.ui.mypage.mypagescreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseFragment
import com.dnd.niceteam.databinding.FragmentMyPageBinding
import com.dnd.niceteam.ui.mypage.applicationstatus.view.ApplicationStatusActivity
import com.dnd.niceteam.ui.mypage.mypagescreen.viewmodel.MyPageViewModel
import com.dnd.niceteam.ui.mypage.profile.view.ProfileActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val myPageViewModel: MyPageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        initView()
        observeData()
    }

    private fun bind() {
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    private fun initView() {
        with(binding) {
            ivNavigationIcon.setOnClickListener { findNavController().navigateUp() }
            layoutNickname.setOnClickListener {
                startActivity(Intent(requireContext(), ProfileActivity::class.java))
            }
            layoutApplicationStatus.setOnClickListener {
                startActivity(Intent(requireContext(), ApplicationStatusActivity::class.java))
            }
            tvNotificationSettings.setOnClickListener { }
        }
    }

    private fun observeData() {
        with(myPageViewModel) {
            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    experienceCount.collectLatest { count ->
                        handleLevelExperience(count)
                    }
                }
            }
        }
    }

    private fun handleLevelExperience(count: Int) {
        with(binding) {
            val experienceViews = listOf<ImageView>(
                ivExperience1,
                ivExperience2,
                ivExperience3,
                ivExperience4,
                ivExperience5
            )
            experienceViews.forEachIndexed { i, v ->
                v.setImageDrawable(
                    if (i < count) ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.bg_level_experience_fill
                    )
                    else ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.bg_level_experience_empty
                    )
                )
            }
        }
    }
}