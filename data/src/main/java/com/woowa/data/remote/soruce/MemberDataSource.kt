package com.woowa.data.remote.soruce

import com.woowa.data.remote.model.response.ApiResponse
import com.woowa.data.remote.model.response.MemberDto
import com.woowa.data.remote.model.response.MemberUpdateDto
import com.woowa.data.remote.service.MemberService
import com.woowa.domain.model.MemberEdit
import javax.inject.Inject

internal interface MemberDataSource {

    suspend fun getMember(memberId: Int): ApiResponse<MemberDto>

    suspend fun editMember(memberEdit: MemberEdit): ApiResponse<MemberUpdateDto>
}

internal class MemberDataSourceImpl @Inject constructor(
    private val memberService: MemberService
): MemberDataSource {

    override suspend fun getMember(memberId: Int): ApiResponse<MemberDto> {
        return memberService.getMember(memberId)
    }

    override suspend fun editMember(memberEdit: MemberEdit): ApiResponse<MemberUpdateDto> {
        return memberService.editMember(memberEdit)
    }
}