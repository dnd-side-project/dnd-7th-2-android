package com.dnd.niceteam.ui.mypage.profile.view

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseActivity
import com.dnd.niceteam.databinding.ActivityEditNicknameBinding
import com.dnd.niceteam.ui.mypage.profile.viewmodel.EditNicknameViewModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EditNicknameActivity :
    BaseActivity<ActivityEditNicknameBinding>(R.layout.activity_edit_nickname) {

    private val editNicknameViewModel: EditNicknameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        initView()
        observeData()
    }

    private fun bind() {
        with(binding) {
            viewModel = editNicknameViewModel
        }
    }

    private fun initView() {
        with(binding) {
            toolbar.clickedNavigationIcon { finish() }
            etNickname.apply {
                addTextChangedListener {
                    editNicknameViewModel.inputNickname(etNickname.text.toString())
                }
                setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        Log.e(TAG, "xxx Press complete button from keyboard!!")
                    }
                    false
                }
            }
        }
    }

    private fun observeData() {
        with(editNicknameViewModel) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    nickname.collectLatest {
                        validateNickname()
                    }
                }
            }
        }
    }

    private fun validateNickname() {
        editNicknameViewModel.validateNickname(
            handleEmpty = { handleInputEmpty(binding.layoutEtNickname) },
            handleInputSuccess = { handleInputSuccess(binding.layoutEtNickname) },
            handleInputError = {
                handleInputError(
                    binding.layoutEtNickname,
                    "공백없이 한글 2~8글자로 입력해주세요."
                )
            }
        )
    }

    private fun handleInputEmpty(textInputLayout: TextInputLayout) {
        textInputLayout.error = null
        textInputLayout.isErrorEnabled = false
    }

    private fun handleInputError(textInputLayout: TextInputLayout, message: String) {
        textInputLayout.error = message
    }

    private fun handleInputSuccess(textInputLayout: TextInputLayout) {
        textInputLayout.error = null
        textInputLayout.isErrorEnabled = false
    }

    companion object {
        private const val TAG = "EditNicknameActivity"
        const val NICKNAME = "nickname"
    }
}