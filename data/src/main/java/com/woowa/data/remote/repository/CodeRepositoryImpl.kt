package com.woowa.data.remote.repository

import com.woowa.data.remote.soruce.CodeDataSource
import com.woowa.domain.model.ApiResult
import com.woowa.domain.model.Code
import com.woowa.domain.model.ErrorResult
import com.woowa.domain.repository.CodeRepository
import javax.inject.Inject

internal class CodeRepositoryImpl @Inject constructor(
    private val codeDataSource: CodeDataSource
) : CodeRepository {

    override suspend fun getCode(codeTypes: List<String>): ApiResult<Code> {
        val code = codeDataSource.getCode(codeTypes)
        return ApiResult(
            code.success,
            code.data?.toCode(),
            ErrorResult.of(code.error.message, code.error.errors)
        )
    }

    override suspend fun getCodeAll(): ApiResult<Code> {
        val code = codeDataSource.getCodeAll()
        return ApiResult(
            code.success,
            code.data?.toCode(),
            ErrorResult.of(code.error.message, code.error.errors)
        )
    }
}