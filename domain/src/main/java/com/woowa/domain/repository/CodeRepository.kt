package com.woowa.domain.repository

import com.woowa.domain.model.ApiResult
import com.woowa.domain.model.Code

interface CodeRepository {

    suspend fun getCode(codeTypes: List<String>): ApiResult<Code>

    suspend fun getCodeAll(): ApiResult<Code>
}