package com.woowa.data.remote.soruce

import com.woowa.data.remote.model.response.ApiResponse
import com.woowa.data.remote.model.response.CodeDto
import com.woowa.data.remote.service.CodeService
import javax.inject.Inject

internal interface CodeDataSource {

    suspend fun getCode(codeTypes: List<String>): ApiResponse<CodeDto>

    suspend fun getCodeAll(): ApiResponse<CodeDto>
}

internal class CodeDataSourceImpl @Inject constructor(
    private val codeService: CodeService
) : CodeDataSource {

    override suspend fun getCode(codeTypes: List<String>): ApiResponse<CodeDto> {
        return codeService.getCode(codeTypes)
    }

    override suspend fun getCodeAll(): ApiResponse<CodeDto> {
        return codeService.getCodeAll()
    }
}