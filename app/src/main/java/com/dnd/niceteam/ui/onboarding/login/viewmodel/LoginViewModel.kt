package com.dnd.niceteam.ui.onboarding.login.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    var email = MutableStateFlow<String>("")
    var password = MutableStateFlow<String>("")

    fun validateEmail(handleInputSuccess: () -> Unit, handleInputError: () -> Unit) {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return when {
            email.value.isEmpty() -> {
                handleInputSuccess()
            }
            !email.value.matches(emailPattern.toRegex()) -> {
                handleInputError()
            }
            else -> {
                handleInputSuccess()
            }
        }
    }
}