package com.woowa.data.remote.model.response.university

import com.woowa.domain.model.university.University

internal data class UniversityDto(val id: Long, val name: String, val emailDomain: String) {
    fun toUniversity(): University = University(id, name, emailDomain)
}
