package com.dnd.niceteam.ui.onboarding

/**
 * @description : 유효성 통과 true
 *                유효성 실패 false
 *                빈 문자열 null
 * @since 2022.08.14
 */
object Validation {

    fun validateEmail(email: String): Boolean? {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return when {
            email.isEmpty() -> null
            !email.matches(emailPattern.toRegex()) -> false
            else -> true
        }
    }

    fun validatePassword(password: String): Boolean? {
        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d~!@#$%^&*()+|=]{8,20}$"
        return when {
            password.isEmpty() -> null
            !password.matches(passwordPattern.toRegex()) -> false
            else -> true
        }
    }

    fun validateNickname(nickname: String): Boolean? {
        val nicknamePattern = "^[가-힣]*$"
        return when {
            nickname.isEmpty() -> null
            !nicknamePattern.matches(nicknamePattern.toRegex()) -> false
            else -> true
        }
    }
}