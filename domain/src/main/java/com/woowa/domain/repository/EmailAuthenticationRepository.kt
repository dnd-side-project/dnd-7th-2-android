package com.woowa.domain.repository

import com.woowa.domain.model.ApiResult
import com.woowa.domain.model.email.Email
import com.woowa.domain.model.email.EmailAuth
import com.woowa.domain.model.email.EmailCheck

interface EmailAuthenticationRepository {

    suspend fun sendEmail(email: Email): ApiResult<*>

    suspend fun authEmail(emailAuth: EmailAuth): ApiResult<EmailCheck>
}