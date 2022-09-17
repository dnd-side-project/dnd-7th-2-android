package com.woowa.domain.model.mywrote

import com.woowa.domain.model.code.Type

data class MyWrote(
    val page: Int,
    val perSize: Int,
    val totalCount: Int,
    val totalPages: Int,
    val prev: Boolean,
    val next: Boolean,
    val contents: List<MyWroteContents>
)

data class MyWroteContents(
    val id: Int,
    val title: String,
    val type: Type,
    val status: String,
    val commentCount: Int,
    val bookmarkCount: Int,
    val projectName: String,
    val professor: String?,
    val field: String?,
    val fieldCategory: String?,
    val createdDate: String
)