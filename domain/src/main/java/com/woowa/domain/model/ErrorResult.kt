package com.woowa.domain.model

import com.woowa.domain.model.error.FieldError

data class ErrorResult(val message: String, val errors: List<FieldError>) {

    companion object {
        fun of(): ErrorResult = ErrorResult(
            message = "",
            errors = emptyList()
        )

        fun of(message: String, errors: List<FieldError>) = ErrorResult(
            message = message,
            errors = errors
        )
    }
}
