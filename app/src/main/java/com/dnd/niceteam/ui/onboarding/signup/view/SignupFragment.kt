package com.dnd.niceteam.ui.onboarding.signup.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseFragment
import com.dnd.niceteam.databinding.FragmentSignupBinding
import com.dnd.niceteam.ui.common.teamGooToastMessage
import com.dnd.niceteam.ui.onboarding.signup.adapter.SearchAdapter
import com.dnd.niceteam.ui.onboarding.signup.viewmodel.SignupViewModel
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignupFragment : BaseFragment<FragmentSignupBinding>(R.layout.fragment_signup) {

    private val signUpViewModel: SignupViewModel by activityViewModels()
    private val searchAdapter by lazy {
        SearchAdapter {
            signUpViewModel.school.value = it
            binding.rvSearch.isGone = true
            binding.btnCertification.isVisible = true
        }
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
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                signUpViewModel.school.collectLatest {
                    when {
                        it.isNotEmpty() -> {
                            binding.rvSearch.isVisible = true
                            binding.btnErase.isVisible = true
                            binding.btnCertification.isGone = true
                        }
                        else -> {
                            binding.rvSearch.isGone = true
                            binding.btnErase.isGone = true
                            binding.btnCertification.isVisible = true
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                signUpViewModel.searchList.collectLatest {
                    searchAdapter.submitList(it)
                }
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                signUpViewModel.email.collectLatest {
                    validateEmail()
                }
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                signUpViewModel.certificationSendEvent.collectLatest {
                    binding.llAgree.isGone = true
                    binding.llCertification.isVisible = true
                    binding.btnCheckCertification.isVisible = true
                }
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                signUpViewModel.passwordEvent.collectLatest {
                    if (it) {
                        requireContext().teamGooToastMessage("학교인증에 성공했어요")?.show()
                        findNavController().navigate(R.id.action_signupFragment_to_passwordFragment)
                    } else {
                        requireContext().teamGooToastMessage("인증번호를 확인해주세요.")?.show()
                    }
                }
            }
        }
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

    private fun bind() {
        binding.viewmodel = signUpViewModel
        binding.rvSearch.adapter = searchAdapter
        binding.etSchoolName.setOnFocusChangeListener { view, isFocus ->
            if (!isFocus) {
                binding.rvSearch.isGone = true
                binding.btnCertification.isVisible = true
            }
        }
        binding.toolbar.clickedNavigationIcon { findNavController().navigateUp() }
        binding.tgAgree.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> validateEmail()
                false -> binding.btnCertification.isGone = true
            }
        }
    }

    private fun handleInputEmpty(textInputLayout: TextInputLayout) {
        textInputLayout.error = null
        textInputLayout.isErrorEnabled = false
        binding.btnCertification.isGone = true
    }

    private fun handleInputError(textInputLayout: TextInputLayout, message: String) {
        textInputLayout.error = message
        binding.btnCertification.isGone = true
    }

    private fun handleInputSuccess(textInputLayout: TextInputLayout) {
        textInputLayout.error = null
        textInputLayout.isErrorEnabled = false
        if (binding.tgAgree.isChecked)
            binding.btnCertification.isVisible = true
    }
}