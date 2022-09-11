package com.woowa.data.remote.repository

import com.woowa.data.remote.service.UniversityService
import com.woowa.domain.model.ApiResult
import com.woowa.domain.model.ErrorResult
import com.woowa.domain.model.university.Department
import com.woowa.domain.model.university.University
import com.woowa.domain.repository.UniversityRepository
import javax.inject.Inject

internal class UniversityRepositoryImpl @Inject constructor(
    private val universityService: UniversityService,
) : UniversityRepository {

    override suspend fun searchUniversity(name: String): ApiResult<List<University>> {
        val result = universityService.searchUniversity(name)
        return ApiResult<List<University>>(
            success = true,
            data = result.data?.map { it.toUniversity() },
            error = ErrorResult.of()
        )
    }

    override suspend fun getUniversityDepartments(
        universityId: Long,
        name: String,
    ): ApiResult<List<Department>> {
        val result = universityService.getUniversityDepartments(universityId, name)
        return ApiResult<List<Department>>(
            success = true,
            data = result.data?.map { it.toDepartment() },
            error = ErrorResult.of()
        )
    }


}