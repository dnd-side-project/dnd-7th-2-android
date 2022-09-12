package com.woowa.data.remote.service

import com.woowa.data.remote.model.response.ApiResponse
import com.woowa.data.remote.model.response.mywrote.MyWroteDto
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MyWroteService {

    @GET("recruiting/me")
    suspend fun getMyWrote(
        @Query("page") page: Int,
        @Query("perSize") perSize: Int,
        @Query("status") status: String
    ): ApiResponse<MyWroteDto>
}