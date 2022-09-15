package com.woowa.data.remote.model.response.member

import com.woowa.domain.model.code.Field
import com.woowa.domain.model.code.PersonalityAdjective
import com.woowa.domain.model.code.PersonalityNoun
import com.woowa.domain.model.code.ReviewTag
import com.woowa.domain.model.member.Member
import com.woowa.domain.model.member.Personality

internal data class MemberDto(
    val id: Int,
    val nickname: String,
    val personality: PersonalityDto,
    val departmentName: String,
    val interestingFields: List<Field>,
    val admissionYear: Int,
    val introduction: String,
    val introductionUrl: String,
    val level: Int,
    val participationPct: Double,
    val reviewTagToNums: Map<ReviewTag, Int>,
    val numTotalEndProject: Int,
    val numCompleteProject: Int
) {
    fun toMember(): Member =
        Member(
            id,
            nickname,
            personality.toPersonality(),
            departmentName,
            interestingFields.map { it.title },
            admissionYear,
            introduction,
            introductionUrl,
            level,
            participationPct,
            reviewTagToNums.mapKeys { it.key.title },
            numTotalEndProject,
            numCompleteProject
        )
}

internal data class PersonalityDto(
    val adjective: PersonalityAdjective,
    val noun: PersonalityNoun
) {
    fun toPersonality(): Personality = Personality(adjective.title, noun.title)
}

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