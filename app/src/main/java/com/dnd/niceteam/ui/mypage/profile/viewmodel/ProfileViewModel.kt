package com.dnd.niceteam.ui.mypage.profile.viewmodel

import androidx.lifecycle.ViewModel
import com.dnd.niceteam.ui.mypage.profile.adapter.Model
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    val profileRankList = MutableStateFlow(
        listOf(
            Model(0, 1, "책임감 굿", "+14"),
            Model(1, 2, "마감을 칼같이", "+9"),
            Model(2, 3, "시간매너 끝판왕", "+8"),
            Model(3, 4, "긍정 태도왕", "+5"),
            Model(4, 4, "아이디어 요정", "+5"),
            Model(4, 5, "활발한 피드백", "+2")
        )
    )
    val allProfileRank = MutableStateFlow(false)

    fun showAllProfileRank() {
        allProfileRank.value = true
    }
}