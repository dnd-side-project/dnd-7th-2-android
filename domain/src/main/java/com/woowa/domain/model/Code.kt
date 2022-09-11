package com.woowa.domain.model

data class Code(
    val codeEnum: Map<String, List<CodeEnum>>
)

data class CodeEnum(
    val code: String,
    val title: String
)