package com.woowa.data.remote.model.response

import com.woowa.domain.model.Member
import com.woowa.domain.model.Personality

internal data class MemberDto(
    val id: Int,
    val nickname: String,
    val personality: PersonalityDto,
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
) {
    fun toMember(): Member =
        Member(
            id,
            nickname,
            personality.toPersonality(),
            departmentName,
            interestingFields,
            admissionYear,
            introduction,
            introductionUrl,
            level,
            participationPct,
            reviewTagToNums,
            numTotalEndProject,
            numCompleteProject
        )
}

internal data class PersonalityDto(
    val adjective: String,
    val noun: String
) {
    fun toPersonality(): Personality = Personality(adjective, noun)
}

/**
 * ReviewTag
 * RESPONSIBILITY : 책임감 굿
 * PUNCTUALITY :    마감을 칼같이
 * MOOD_MAKER :     분위기 메이커
 * TIME_MANNERS :   시간매너 끝판왕
 * POSITIVE :       긍정 태도왕
 * IDEA :           아이디어 요정
 * FEEDBACK :       활발한 피드백
 * DECISIVE :       속전속결 피드백
 * LIKE_MINE :      남의 일도 내 일같이
 */
//@JsonClass(generateAdapter = true)
//data class MemberDto(
//    @field:Json(name = "id") val id: Int,
//    @field:Json(name = "nickname") val nickname: String,
//    @field:Json(name = "personality") val personality: Personality,
//    @field:Json(name = "departmentName") val departmentName: String,
//    @field:Json(name = "interestingFields") val interestingFields: List<String>,
//    @field:Json(name = "admissionYear") val admissionYear: Int,
//    @field:Json(name = "introduction") val introduction: String,
//    @field:Json(name = "introductionUrl") val introductionUrl: String,
//    @field:Json(name = "level") val level: Int,
//    @field:Json(name = "participationPct") val participationPct: Float,
//    @field:Json(name = "reviewTagToNums") val reviewTagToNums: ReviewTagToNums,
//    @field:Json(name = "numTotalEndProject") val numTotalEndProject: Int,
//    @field:Json(name = "numCompleteProject") val numCompleteProject: Int
//
//) {
//    @JsonClass(generateAdapter = true)
//    data class Personality(
//        @field:Json(name = "adjective") val adjective: String,
//        @field:Json(name = "noun") val noun: String
//    )
//
//    @JsonClass(generateAdapter = true)
//    data class ReviewTagToNums(
//        @field:Json(name = "RESPONSIBILITY") val RESPONSIBILITY: Int,
//        @field:Json(name = "TIME_MANNERS") val TIME_MANNERS: Int,
//        @field:Json(name = "DEAD_LINE") val DEAD_LINE: Int,
//        @field:Json(name = "MOOD_MAKER") val MOOD_MAKER: Int
//    )
//}