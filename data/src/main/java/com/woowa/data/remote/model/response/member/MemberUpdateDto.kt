package com.woowa.data.remote.model.response.member

import com.woowa.domain.model.member.MemberUpdate

internal data class MemberUpdateDto(val id: Int) {
    fun toMemberUpdate(): MemberUpdate = MemberUpdate(id)
}

//@JsonClass(generateAdapter = true)
//class MemberUpdateDto(
//    @field:Json(name = "id") val id: Int
//)