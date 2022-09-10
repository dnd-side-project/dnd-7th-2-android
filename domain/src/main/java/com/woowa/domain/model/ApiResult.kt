package com.woowa.domain.model

data class ApiResult<T>(val success: Boolean, val data: T? = null, val error: ErrorResult = ErrorResult.of())
