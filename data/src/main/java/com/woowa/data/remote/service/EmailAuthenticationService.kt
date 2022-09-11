package com.woowa.data.remote.service

import com.woowa.data.remote.model.response.ApiResponse
import com.woowa.data.remote.model.response.email.EmailCheckDto
import com.woowa.domain.model.email.Email
import com.woowa.domain.model.email.EmailAuth
import retrofit2.http.Body
import retrofit2.http.POST

internal interface EmailAuthenticationService {

    @POST("/email-auth/send?_csrf=197952e5-9301-4b7f-9581-d499b76851db")
    suspend fun sendEmail(@Body email: Email): ApiResponse<*>

    @POST("/email-auth/check?_csrf=50deb17e-c5de-40e5-8494-a092ef1395dc")
    suspend fun authEmail(@Body emailAuth: EmailAuth): ApiResponse<EmailCheckDto>
}