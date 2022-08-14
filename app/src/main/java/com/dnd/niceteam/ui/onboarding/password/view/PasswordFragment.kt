package com.dnd.niceteam.ui.onboarding.password.view

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
import com.dnd.niceteam.databinding.FragmentPasswordBinding
import com.dnd.niceteam.ui.common.teamGooToastMessage
import com.dnd.niceteam.ui.onboarding.signup.viewmodel.SignupViewModel
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PasswordFragment : BaseFragment<FragmentPasswordBinding>(R.layout.fragment_password) {

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
                signUpViewModel.email.collectLatest {
                    validateEmail()
                }
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                signUpViewModel.password.collectLatest {
                    validatePassword()
                }
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                signUpViewModel.nickEvent.collectLatest {
                    requireContext().teamGooToastMessage("학교인증에 성공했어요")
                    findNavController().navigate(R.id.action_passwordFragment_to_nickNameFragment)
                }
            }
        }
    }

    private fun bind() {
        binding.viewmodel = signUpViewModel
        binding.toolbar.clickedNavigationIcon { findNavController().navigateUp() }
    }

    private fun validateEmail() {
        signUpViewModel.validateEmail(
            handleEmpty = { handleInputEmpty(binding.etSchoolEmail) },
            handleInputSuccess = { handleInputSuccess(binding.etSchoolEmail) },
            handleInputError = {
                handleInputError(
                    binding.etSchoolEmail,
                    "이메일 형식이 확인해주세요."
                )
            }
        )
    }

    private fun validatePassword() {
        signUpViewModel.validatePassword(
            handleEmpty = { handleInputEmpty(binding.etPasswordTitle) },
            handleInputSuccess = { handleInputSuccess(binding.etPasswordTitle) },
            handleInputError = {
                handleInputError(
                    binding.etPasswordTitle,
                    "비밀번호 형식이 확인해주세요."
                )
            }
        )
    }

    private fun handleInputEmpty(textInputLayout: TextInputLayout) {
        textInputLayout.error = null
    }

    private fun handleInputError(textInputLayout: TextInputLayout, message: String) {
        textInputLayout.error = message
    }

    private fun handleInputSuccess(textInputLayout: TextInputLayout) {
        textInputLayout.error = null
        textInputLayout.isErrorEnabled = false
    }
}