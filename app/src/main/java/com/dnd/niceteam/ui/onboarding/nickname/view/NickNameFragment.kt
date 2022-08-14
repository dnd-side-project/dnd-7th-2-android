package com.dnd.niceteam.ui.onboarding.nickname.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseFragment
import com.dnd.niceteam.databinding.FragmentNicknameBinding
import com.dnd.niceteam.ui.onboarding.signup.viewmodel.SignupViewModel
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NickNameFragment : BaseFragment<FragmentNicknameBinding>(R.layout.fragment_nickname) {

    private val signUpViewModel: SignupViewModel by activityViewModels()

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
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                signUpViewModel.nickname.collectLatest {
                    validateNickname()
                }
            }
        }
    }

    private fun bind() {
        binding.viewmodel = signUpViewModel
        binding.toolbar.clickedNavigationIcon { findNavController().navigateUp() }
    }

    private fun validateNickname() {
        signUpViewModel.validateNickname(
            handleEmpty = { handleInputEmpty(binding.etNicknameTitle) },
            handleInputSuccess = { handleInputSuccess(binding.etNicknameTitle) },
            handleInputError = {
                handleInputError(
                    binding.etNicknameTitle,
                    "닉네임 형식이 확인해주세요."
                )
            }
        )
    }

    private fun handleInputEmpty(textInputLayout: TextInputLayout) {
        textInputLayout.error = null
        textInputLayout.isErrorEnabled = false
    }

    private fun handleInputError(
        textInputLayout: TextInputLayout,
        message: String,
    ) {
        textInputLayout.error = message
    }

    private fun handleInputSuccess(textInputLayout: TextInputLayout) {
        textInputLayout.error = null
        textInputLayout.isErrorEnabled = false
    }
}