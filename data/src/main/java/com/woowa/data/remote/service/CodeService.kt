package com.woowa.data.remote.service

import com.woowa.data.remote.model.response.ApiResponse
import com.woowa.data.remote.model.response.CodeDto
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CodeService {

    @GET("code")
    suspend fun getCode(@Query("codeTypes") codeTypes: List<String>): ApiResponse<CodeDto>

    @GET("code")
    suspend fun getCodeAll(): ApiResponse<CodeDto>
}