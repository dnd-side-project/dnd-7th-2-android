package com.woowa.data.remote.model.response.university

import com.woowa.domain.model.university.Department

/**
 * @description id(아이디), collegeName(단과대학), name(학과명)
 */
internal data class DepartmentDto(val id: Long, val collegeName: String, val name: String) {
    fun toDepartment(): Department = Department(id, collegeName, name)
}
