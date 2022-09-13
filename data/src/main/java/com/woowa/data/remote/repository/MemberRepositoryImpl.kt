package com.woowa.data.remote.repository

import com.woowa.data.remote.soruce.MemberDataSource
import com.woowa.domain.model.*
import com.woowa.domain.model.member.Member
import com.woowa.domain.model.member.MemberEdit
import com.woowa.domain.model.member.MemberUpdate
import com.woowa.domain.repository.MemberRepository
import javax.inject.Inject

internal class MemberRepositoryImpl @Inject constructor(
    private val memberDataSource: MemberDataSource
) : MemberRepository {

    override suspend fun getMember(memberId: Int): ApiResult<Member> {
        val member = memberDataSource.getMember(memberId)
        return ApiResult(
            member.success,
            member.data?.toMember(),
            ErrorResult.of(member.error.message, member.error.errors)
        )
    }

    override suspend fun editMember(memberEdit: MemberEdit): ApiResult<MemberUpdate> {
        val update = memberDataSource.editMember(memberEdit)
        return ApiResult(
            update.success,
            update.data?.toMemberUpdate(),
            ErrorResult.of(update.error.message, update.error.errors)
        )
    }
}