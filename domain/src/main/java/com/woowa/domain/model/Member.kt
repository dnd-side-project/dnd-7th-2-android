package com.woowa.domain.model

data class Member(
    val id: Int,
    val nickname: String,
    val personality: Personality,
    val departmentName: String,
    val interestingFields: List<String>,
    val admissionYear: Int,
    val introduction: String,
    val introductionUrl: String,
    val level: Int,
    val participationPct: Double,
    val reviewTagToNums: Map<String, Int>,
    val numTotalEndProject: Int,
    val numCompleteProject: Int
)

data class Personality(
    val adjective: String,
    val noun: String
)