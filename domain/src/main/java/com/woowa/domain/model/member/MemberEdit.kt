package com.woowa.domain.model.member

data class MemberEdit(
    val nickname: String,
    val personalityAdjective: String,
    val personalityNoun: String,
    val interestingFields: List<String>,
    val introduction: String,
    val introductionUrl: String
)