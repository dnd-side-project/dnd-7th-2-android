package com.woowa.data.remote.model.response

internal data class ApiResponse<T>(val success: Boolean, val data: T? = null, val error: ErrorResponse = ErrorResponse.of())