package com.dnd.niceteam.ui.mypage.profile.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.dnd.niceteam.ui.mypage.profile.view.EditNicknameActivity.Companion.NICKNAME
import com.dnd.niceteam.ui.onboarding.Validation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class EditNicknameViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val nickname = MutableStateFlow<String>(
        savedStateHandle[NICKNAME] ?: throw IllegalStateException("닉네임이 없습니다.")
    )

    fun inputNickname(input: String) {
        nickname.value = input
    }

    fun validateNickname(
        handleEmpty: () -> Unit,
        handleInputSuccess: () -> Unit,
        handleInputError: () -> Unit
    ) {
        when (Validation.validateNickname(nickname.value)) {
            null -> handleEmpty()
            false -> handleInputError()
            true -> handleInputSuccess()
        }
    }
}