package com.woowa.data.remote.repository

import com.woowa.data.remote.service.EmailAuthenticationService
import com.woowa.domain.model.ApiResult
import com.woowa.domain.model.email.Email
import com.woowa.domain.model.email.EmailAuth
import com.woowa.domain.model.email.EmailCheck
import com.woowa.domain.repository.EmailAuthenticationRepository
import javax.inject.Inject

internal class EmailAuthenticationRepositoryImpl @Inject constructor(
    private val emailAuthService: EmailAuthenticationService,
) : EmailAuthenticationRepository {

    override suspend fun sendEmail(email: Email): ApiResult<*> {
        val result = emailAuthService.sendEmail(email)
        return ApiResult(result.success, null)
    }

    override suspend fun authEmail(emailAuth: EmailAuth): ApiResult<EmailCheck> {
        val result = emailAuthService.authEmail(emailAuth)
        return ApiResult(result.success, result.data?.toEmailCheck())
    }
}