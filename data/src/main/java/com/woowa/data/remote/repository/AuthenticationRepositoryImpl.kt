package com.woowa.data.remote.repository

import com.woowa.data.remote.service.AuthenticationService
import com.woowa.domain.model.*
import com.woowa.domain.repository.AuthenticationRepository
import javax.inject.Inject

internal class AuthenticationRepositoryImpl @Inject constructor(
    private val auth: AuthenticationService
): AuthenticationRepository {

    override suspend fun login(login: Login): ApiResult<Token> {
        val token = auth.login(login)
        return ApiResult(
            token.success,
            token.data?.toToken(),
            ErrorResult.of(token.error.message, token.error.errors)
        )
    }

    override suspend fun logout(): ApiResult<*> {
        val result = auth.logout()
        return ApiResult(result.success, null)
    }

    override suspend fun reissue(reissue: Reissue): ApiResult<Token> {
        val token = auth.reissue(reissue)
        return ApiResult(
            token.success,
            token.data?.toToken(),
            ErrorResult.of(token.error.message, token.error.errors)
        )
    }
}