package com.woowa.data.remote.model.response.email

import com.woowa.domain.model.email.EmailCheck

internal data class EmailCheckDto(val email: String, val authenticated: Boolean) {
    fun toEmailCheck(): EmailCheck = EmailCheck(email, authenticated)
}
