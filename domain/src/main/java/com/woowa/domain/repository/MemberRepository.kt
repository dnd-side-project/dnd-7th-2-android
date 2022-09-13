package com.woowa.domain.repository

import com.woowa.domain.model.ApiResult
import com.woowa.domain.model.member.Member
import com.woowa.domain.model.member.MemberEdit
import com.woowa.domain.model.member.MemberUpdate

interface MemberRepository {

    suspend fun getMember(memberId: Int): ApiResult<Member>

    suspend fun editMember(memberEdit: MemberEdit): ApiResult<MemberUpdate>
}