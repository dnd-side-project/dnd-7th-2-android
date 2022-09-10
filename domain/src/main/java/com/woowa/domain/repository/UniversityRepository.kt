package com.woowa.domain.repository

import com.woowa.domain.model.ApiResult
import com.woowa.domain.model.university.Department
import com.woowa.domain.model.university.University

interface UniversityRepository {

    suspend fun searchUniversity(name: String): ApiResult<List<University>>

    suspend fun getUniversityDepartments(
        universityId: Long,
        name: String,
    ): ApiResult<List<Department>>

}