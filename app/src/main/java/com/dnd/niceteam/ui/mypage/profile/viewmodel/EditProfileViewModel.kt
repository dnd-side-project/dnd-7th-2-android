package com.dnd.niceteam.ui.mypage.profile.viewmodel

import androidx.lifecycle.ViewModel
import com.dnd.niceteam.ui.mypage.profile.adapter.MyTitle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor() : ViewModel() {

    val allInterestList = MutableStateFlow(
        listOf(
            "# 광고/마케팅",
            "# 기획/디자인",
            "# 디자인",
            "# IT/소프트웨어/게임",
            "# 미디어",
            "# 예체능",
            "# 어학",
            "# 금융/회계",
            "# 기타"
        )
    )
    val selectedInterestList = MutableStateFlow(
        listOf(
            "# 광고/마케팅",
            "# 미디어"
        )
    )

    val allAdjectiveTitleList = MutableStateFlow(
        listOf(
            MyTitle("⚖️", "논리적인"),
            MyTitle("\uD83D\uDDD3", "계획적인"),
            MyTitle("🔍", "꼼꼼한"),
            MyTitle("\uD83D\uDEA8", "신속한"),
            MyTitle("🤡", "쾌활한"),
            MyTitle("🔮", "창의적인"),
            MyTitle("🐢", "성실한"),
            MyTitle("🎯", "목표지향적"),
            MyTitle("💪", "끈기있는")
        )
    )
    val selectedAdjectiveTitle = MutableStateFlow("목표지향적")

    val allNounTitleList = MutableStateFlow(
        listOf(
            MyTitle("\uD83D\uDE4B\uD83C\uDFFB", "리더"),
            MyTitle("\uD83D\uDC81\uD83C\uDFFB", "팔로워"),
            MyTitle("\uD83E\uDDDD\uD83C\uDFFB", "커뮤니케이터"),
            MyTitle("\uD83D\uDC69\uD83C\uDFFB\u200D\uD83C\uDFEB", "완벽주의자"),
            MyTitle("\uD83D\uDC68\uD83C\uDFFB\u200D\uD83D\uDE80", "모험가"),
            MyTitle("\uD83D\uDC68\uD83C\uDFFB\u200D\uD83D\uDD2C", "발명가"),
            MyTitle("\uD83D\uDD75\uD83C\uDFFB", "분석가"),
            MyTitle("\uD83D\uDC69\uD83C\uDFFB\u200D⚖️", "중재자"),
            MyTitle("\uD83E\uDD39\uD83C\uDFFB", "만능재주꾼")
        )
    )
    val selectedNounTitle = MutableStateFlow("커뮤니케이터")

    fun selectAdjectiveTitle(title: String) {
        selectedAdjectiveTitle.value = title
    }

    fun selectNounTitle(title: String) {
        selectedNounTitle.value = title
    }
}