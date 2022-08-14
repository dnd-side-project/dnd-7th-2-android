package com.dnd.niceteam.ui.onboarding.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnd.niceteam.ui.onboarding.Validation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SignupViewModel @Inject constructor() : ViewModel() {

    private val result = "123456"

    var school = MutableStateFlow("")

    var email = MutableStateFlow("")

    var password = MutableStateFlow("")

    var nickname = MutableStateFlow("")

    var certificationNumber = MutableStateFlow("")

    private val _certificationSendEvent = MutableSharedFlow<Unit>()
    val certificationSendEvent = _certificationSendEvent.asSharedFlow()

    private val _passwordEvent = MutableSharedFlow<Boolean>()
    val passwordEvent = _passwordEvent.asSharedFlow()

    private val _nickEvent = MutableSharedFlow<Unit>()
    val nickEvent = _nickEvent.asSharedFlow()

    val searchList: StateFlow<List<String>> = school
        .debounce(300)
        .mapLatest { query ->
            listOf("남", "태", "우", "서", "준", "형")
        }.stateIn(
            initialValue = listOf(""),
            started = SharingStarted.WhileSubscribed(300),
            scope = viewModelScope,
        )

    fun validateEmail(
        handleEmpty: () -> Unit,
        handleInputSuccess: () -> Unit,
        handleInputError: () -> Unit
    ) {
        when (Validation.validateEmail(email.value)) {
            null -> handleEmpty()
            false -> handleInputError()
            true -> handleInputSuccess()
        }
    }

    fun validatePassword(
        handleEmpty: () -> Unit,
        handleInputSuccess: () -> Unit,
        handleInputError: () -> Unit
    ) {
        when (Validation.validatePassword(password.value)) {
            null -> handleEmpty()
            false -> handleInputError()
            true -> handleInputSuccess()
        }
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

    fun navigateNickName() = viewModelScope.launch {
        _nickEvent.emit(Unit)
    }

    fun navigatePassword() = viewModelScope.launch {
        if (certificationNumber.value == result) _passwordEvent.emit(true)
        else _passwordEvent.emit(false)
    }

    fun receiveCertification() = viewModelScope.launch {
        _certificationSendEvent.emit(Unit)
    }

    fun eraseSchool() {
        school.value = ""
    }
}