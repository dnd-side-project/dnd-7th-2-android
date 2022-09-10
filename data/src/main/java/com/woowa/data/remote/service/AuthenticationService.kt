package com.woowa.data.remote.service

import com.woowa.data.remote.model.response.ApiResponse
import com.woowa.data.remote.model.response.TokenDto
import com.woowa.domain.model.Login
import com.woowa.domain.model.Reissue
import retrofit2.http.Body
import retrofit2.http.POST

internal interface AuthenticationService {

    @POST("/login")
    suspend fun login(@Body login: Login): ApiResponse<TokenDto>

    @POST("/logout")
    suspend fun logout(): ApiResponse<*>

    @POST("/auth/reissue")
    suspend fun reissue(@Body reissue: Reissue): ApiResponse<TokenDto>
}