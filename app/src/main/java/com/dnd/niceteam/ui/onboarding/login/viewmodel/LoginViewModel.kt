package com.dnd.niceteam.ui.onboarding.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnd.niceteam.ui.onboarding.Validation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    var email = MutableStateFlow<String>("")
    var password = MutableStateFlow<String>("")

    private val _signUpEvent = MutableSharedFlow<Unit>()
    val signUpEvent = _signUpEvent.asSharedFlow()

    private val _loginEvent = MutableSharedFlow<Boolean>()
    val loginEvent = _loginEvent.asSharedFlow()

    fun validateEmail(
        handleInputSuccess: () -> Unit,
        handleInputError: () -> Unit
    ) {
        when (Validation.validateEmail(email.value)) {
            null -> handleInputSuccess()
            false -> handleInputError()
            true -> handleInputSuccess()
        }
    }

    fun clickedLogin() = viewModelScope.launch {
        if(email.value.isEmpty() || password.value.isEmpty()) _loginEvent.emit(false)
        else _loginEvent.emit(true)
    }

    fun navigateToSignUp() = viewModelScope.launch {
        _signUpEvent.emit(Unit)
    }
}