package com.woowa.data.remote.model.response

import com.woowa.domain.model.error.FieldError

internal data class ErrorResponse(val code: String, val message: String, val errors: List<FieldError>) {

    companion object {
        fun of(): ErrorResponse = ErrorResponse(
            code = "",
            message = "",
            errors = emptyList()
        )
    }
}
