package com.dnd.niceteam.ui.onboarding.nickname.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseFragment
import com.dnd.niceteam.databinding.FragmentNicknameCompleteBinding
import com.dnd.niceteam.ui.onboarding.signup.viewmodel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NickNameCompleteFragment() :
    BaseFragment<FragmentNicknameCompleteBinding>(R.layout.fragment_nickname_complete) {

    private val signUpViewModel: SignupViewModel by activityViewModels()
    private val animTransTwits by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.anim_rotate
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view?.let { findNavController().navigateUp() }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        bind()
        observeData()
    }

    private fun observeData() {
    }

    private fun bind() {
        binding.viewmodel = signUpViewModel
        binding.ivEgg.startAnimation(animTransTwits)
        binding.toolbar.clickedNavigationIcon { findNavController().navigateUp() }
    }

}