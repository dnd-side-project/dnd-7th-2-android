package com.woowa.data.remote.model.response

import com.woowa.domain.model.Code
import com.woowa.domain.model.CodeEnum

internal data class CodeDto(
    val codeEnum: Map<String, List<CodeEnumDto>>
) {
    fun toCode(): Code =
        Code(codeEnum.mapValues { values -> values.value.map { dto -> dto.toCodeEnum() } })
}

internal data class CodeEnumDto(
    val code: String,
    val title: String
) {
    fun toCodeEnum(): CodeEnum = CodeEnum(code, title)
}

//@JsonClass(generateAdapter = true)
//data class CodeDto(
//    @field:Json(name = "DayOfWeek") val DayOfWeek: List<CodeEnumDto>?,
//    @field:Json(name = "Field") val Field: List<CodeEnumDto>?,
//    @field:Json(name = "FieldCategory") val FieldCategory: List<CodeEnumDto>?,
//    @field:Json(name = "PersonalityAdjective") val PersonalityAdjective: List<CodeEnumDto>?,
//    @field:Json(name = "PersonalityNoun") val PersonalityNoun: List<CodeEnumDto>?,
//    @field:Json(name = "ReviewTag") val ReviewTag: List<CodeEnumDto>?
//) {
//    @JsonClass(generateAdapter = true)
//    data class CodeEnumDto(
//        @field:Json(name = "code") val code: String,
//        @field:Json(name = "title") val title: String
//    )
//}