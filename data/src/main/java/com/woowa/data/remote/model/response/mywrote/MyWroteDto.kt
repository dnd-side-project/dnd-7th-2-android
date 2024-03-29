package com.woowa.data.remote.model.response.mywrote

import com.woowa.domain.model.code.Field
import com.woowa.domain.model.code.FieldCategory
import com.woowa.domain.model.code.Type
import com.woowa.domain.model.mywrote.MyWrote
import com.woowa.domain.model.mywrote.MyWroteContents

internal data class MyWroteDto(
    val page: Int,
    val perSize: Int,
    val totalCount: Int,
    val totalPages: Int,
    val prev: Boolean,
    val next: Boolean,
    val contents: List<MyWroteContentsDto>
) {
    fun toMyWrote(): MyWrote =
        MyWrote(
            page = page,
            perSize = perSize,
            totalCount = totalCount,
            totalPages = totalPages,
            prev = prev,
            next = next,
            contents = contents.map { it.toMyWroteContents() }
        )
}

internal data class MyWroteContentsDto(
    val id: Int,
    val title: String,
    val type: Type,
    val status: String,
    val commentCount: Int,
    val bookmarkCount: Int,
    val projectName: String,
    val professor: String? = null,
    val field: Field? = null,
    val fieldCategory: FieldCategory? = null,
    val createdDate: String
) {
    fun toMyWroteContents(): MyWroteContents =
        MyWroteContents(
            id = id,
            title = title,
            type = type,
            status = status,
            commentCount = commentCount,
            bookmarkCount = bookmarkCount,
            projectName = projectName,
            professor = professor,
            field = field?.title,
            fieldCategory = fieldCategory?.title,
            createdDate = createdDate
        )
}