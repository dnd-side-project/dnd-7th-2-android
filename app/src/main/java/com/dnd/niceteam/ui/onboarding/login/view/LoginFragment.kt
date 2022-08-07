package com.dnd.niceteam.ui.onboarding.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseFragment
import com.dnd.niceteam.databinding.FragmentLoginBinding
import com.dnd.niceteam.ui.onboarding.login.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = loginViewModel
        observeData()
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.email.collectLatest {
                    loginViewModel.validateEmail(
                        handleInputSuccess = { handleInputSuccess(binding.etEmail) },
                        handleInputError = { handleInputError(binding.etEmail, "이메일 형식이 확인해주세요.") }
                    )
                }
            }
        }
    }

    private fun handleInputError(textInputLayout: TextInputLayout, message: String) {
        textInputLayout.error = message
    }

    private fun handleInputSuccess(textInputLayout: TextInputLayout) {
        textInputLayout.error = null
        textInputLayout.isErrorEnabled = false
    }
}