package com.woowa.domain.repository

import com.woowa.domain.model.ApiResult
import com.woowa.domain.model.Login
import com.woowa.domain.model.Reissue
import com.woowa.domain.model.Token

interface AuthenticationRepository {

    suspend fun login(login: Login): ApiResult<Token>

    suspend fun logout(): ApiResult<*>

    suspend fun reissue(reissue: Reissue): ApiResult<Token>
}