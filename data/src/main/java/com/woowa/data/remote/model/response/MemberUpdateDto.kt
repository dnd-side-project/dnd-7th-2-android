package com.woowa.data.remote.model.response

import com.woowa.domain.model.MemberUpdate

internal data class MemberUpdateDto(val id: Int) {
    fun toMemberUpdate(): MemberUpdate = MemberUpdate(id)
}

//@JsonClass(generateAdapter = true)
//class MemberUpdateDto(
//    @field:Json(name = "id") val id: Int
//)