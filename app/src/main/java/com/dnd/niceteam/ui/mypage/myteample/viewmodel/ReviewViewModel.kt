package com.dnd.niceteam.ui.mypage.myteample.viewmodel

import androidx.lifecycle.ViewModel
import com.dnd.niceteam.ui.mypage.myteample.view.Member
import com.dnd.niceteam.ui.mypage.myteample.view.MemberReview
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor() : ViewModel() {

    var memberList = MutableStateFlow(
        listOf(
            Member(1, 1, "닉네임1", "칭호1"),
            Member(2, 4, "닉네임2", "칭호2"),
            Member(3, 3, "닉네임3", "칭호3"),
            Member(4, 2, "닉네임4", "칭호4")
        )
    )

    var reviewTags = MutableStateFlow(
        listOf(
            "아이디어 요정",
            "시간매너 끝판왕",
            "책임감 굿",
            "속전속결 결정왕",
            "분위기 메이커",
            "남의 일도 내 일같이",
            "다재다능 스킬보유자",
            "활발한 피드백",
            "없어요"
        )
    )

    var reviews = MutableStateFlow(emptyArray<MemberReview?>())

    fun initReviews(size: Int) {
        reviews.value = arrayOfNulls(size)
    }

    fun updateReviewTags(position: Int, tags: ArrayList<String>?) {
        val memberId = memberList.value[position].id
        val newReviews = reviews.value.toMutableList()

        if (newReviews[position] == null) {
            newReviews[position] = MemberReview(memberId, MemberReview.Review(tags, null, null))
        } else {
            newReviews[position]?.review?.tags = tags
        }

        reviews.value = newReviews.toTypedArray()
    }

    fun updateParticipationRating(position: Int, participation: Int?) {
        val memberId = memberList.value[position].id
        val newReviews = reviews.value.toMutableList()

        if (newReviews[position] == null) {
            newReviews[position] =
                MemberReview(memberId, MemberReview.Review(null, participation, null))
        } else {
            newReviews[position]?.review?.participation = participation
        }

        reviews.value = newReviews.toTypedArray()
    }

    fun updateHopeTeamAgainRating(position: Int, hopeTeamAgain: Int?) {
        val memberId = memberList.value[position].id
        val newReviews = reviews.value.toMutableList()

        if (newReviews[position] == null) {
            newReviews[position] =
                MemberReview(memberId, MemberReview.Review(null, null, hopeTeamAgain))
        } else {
            newReviews[position]?.review?.hopeTeamAgain = hopeTeamAgain
        }

        reviews.value = newReviews.toTypedArray()
    }

    fun updateSkipReview(position: Int, skip: Boolean) {
        val memberId = memberList.value[position].id
        val newReviews = reviews.value.toMutableList()

        if (newReviews[position] == null) {
            newReviews[position] =
                MemberReview(memberId, MemberReview.Review(null, null, null), skip)
        } else {
            newReviews[position]?.skipReview = skip
        }

        reviews.value = newReviews.toTypedArray()
    }
}