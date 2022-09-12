package com.woowa.domain.model

data class MemberEdit(
    val nickname: String,
    val personalityAdjective: String,
    val personalityNoun: String,
    val interestingFields: List<String>,
    val introduction: String,
    val introductionUrl: String
)