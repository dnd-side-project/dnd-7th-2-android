package com.woowa.data.remote.service

import com.woowa.data.remote.model.response.ApiResponse
import com.woowa.data.remote.model.response.university.DepartmentDto
import com.woowa.data.remote.model.response.university.UniversityDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface UniversityService {

    @GET("/universities")
    suspend fun searchUniversity(@Query("name") name: String): ApiResponse<List<UniversityDto>>

    @GET("/universities/{universityId}/departments")
    suspend fun getUniversityDepartments(
        @Path("universityId") universityId: Long,
        @Query("name") name: String,
    ): ApiResponse<List<DepartmentDto>>
}