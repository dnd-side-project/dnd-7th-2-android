package com.dnd.niceteam.ui.mypage.mywrote.viewmodel

import androidx.lifecycle.ViewModel
import com.dnd.niceteam.ui.mypage.mywrote.adapter.MyComment
import com.dnd.niceteam.ui.mypage.mywrote.adapter.MyRecruitment
import com.dnd.niceteam.ui.mypage.mywrote.view.Recruitment
import com.dnd.niceteam.ui.mypage.mywrote.view.getRecruitmentStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MyWroteViewModel @Inject constructor() : ViewModel() {

    var recruitmentStatus = MutableStateFlow(Recruitment.ALL)

    var curRecruitments = MutableStateFlow(listOf<MyRecruitment>())
    private val allRecruitments = MutableStateFlow(
        listOf(
            MyRecruitment(
                1,
                "모집중",
                "강의",
                "모집글 제목내용입니다. 328픽셀로 제한/가다가 쩜쩜쩜"
            ),
            MyRecruitment(
                1,
                "모집완료",
                "사이드",
                "모집글 제목명"
            ),
            MyRecruitment(
                1,
                "모집중",
                "사이드",
                "\uD83D\uDD25\uD83D\uDD25모집글 제목명 시영디 팀플 같이 하실분 ???? \uD83D\uDD25\uD83D\uDD25"
            )
        )
    )

    var isDelete = MutableStateFlow(false)

    var comments = MutableStateFlow(
        listOf(
            MyComment(
                1,
                "모집글에 대한 댓글/답글 내용입니다.",
                "2022.06.17",
                "강의 or 사이드",
                "모집글 제목명"
            ),
            MyComment(
                2,
                "모집글에 대한 댓글/답글 내용입니다.",
                "2022.06.17",
                "강의 or 사이드",
                "모집글 제목명"
            ),
            MyComment(
                3,
                "모집글에 대한 댓글/답글 내용입니다.",
                "2022.06.17",
                "강의 or 사이드",
                "모집글 제목명"
            )
        )
    )
    var deleteCommentIds = MutableStateFlow(listOf<Int>())

    fun updateRecruitmentStatus(status: Recruitment) {
        recruitmentStatus.value = status
    }

    fun updateRecruitmentList(status: Recruitment): List<MyRecruitment> {
        val newList =
            if (status == Recruitment.ALL) allRecruitments.value
            else allRecruitments.value.filter { it.status == getRecruitmentStatus(status) }
        curRecruitments.value = newList

        return newList
    }

    fun changeDeleteMode() {
        isDelete.value = !isDelete.value
        deleteCommentIds.value = arrayListOf()
    }

    fun setDeleteCommentIds(ids: List<Int>) {
        deleteCommentIds.value = ids
    }

    fun addDeleteCommentId(id: Int) {
        val list = deleteCommentIds.value.toMutableList().apply {
            add(id)
        }
        deleteCommentIds.value = list
    }

    fun removeDeleteCommentId(id: Int) {
        val list = deleteCommentIds.value.toMutableList().apply {
            remove(id)
        }
        deleteCommentIds.value = list
    }

    fun deleteComments() {
        val list = mutableListOf<MyComment>()
        comments.value.forEachIndexed { i, myComment ->
            if (deleteCommentIds.value.isEmpty()) return
            else if (!deleteCommentIds.value.contains(i)) list.add(myComment)
        }
        comments.value = list
    }
}