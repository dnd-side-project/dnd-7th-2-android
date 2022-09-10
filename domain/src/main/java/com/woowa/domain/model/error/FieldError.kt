package com.woowa.domain.model.error

data class FieldError(val field: String, val value: String, val reason: String)
