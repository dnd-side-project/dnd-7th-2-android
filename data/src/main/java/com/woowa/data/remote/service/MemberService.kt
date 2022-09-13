package com.woowa.data.remote.service

import com.woowa.data.remote.model.response.ApiResponse
import com.woowa.data.remote.model.response.member.MemberDto
import com.woowa.data.remote.model.response.member.MemberUpdateDto
import com.woowa.domain.model.member.MemberEdit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

internal interface MemberService {

    @GET("members/{memberId}")
    suspend fun getMember(@Path("memberId") memberId: Int): ApiResponse<MemberDto>

    @PUT("members/?_csrf=57b84808-94b2-434c-afcd-b59db751ab22")
    suspend fun editMember(@Body memberEdit: MemberEdit): ApiResponse<MemberUpdateDto>
}