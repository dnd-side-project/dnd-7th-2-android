package com.dnd.niceteam.ui.mypage.myteample.viewmodel

import androidx.lifecycle.ViewModel
import com.dnd.niceteam.ui.mypage.myteample.adapter.MyTeample
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MyTeampleViewModel @Inject constructor() : ViewModel() {

    var tabPosition = MutableStateFlow(0)

    var teampleList = MutableStateFlow(
        listOf(
            MyTeample(
                0,
                "사이드",
                "프로젝트 명 프로젝트 명 프로젝트 명 길면 두줄로 238 제한",
                "# 카테고리1  # 카테고리2  # 형태(동아리,대외활동등)",
                "",
                "",
                "팀원 4명"
            ),
            MyTeample(
                0,
                "강의",
                "강의명 강의명 강의명 길면 역시 두줄로 238로 사이드와 동일하게 제한",
                "",
                "교수명",
                "월/수 10:30",
                "팀원 4명"
            ),
            MyTeample(
                0,
                "강의",
                "디자인 테크놀로지 융합",
                "",
                "김연지",
                "월/수 9:00",
                "팀원 4명"
            ),
            MyTeample(
                0,
                "사이드",
                "팀구은행 핀테크 산업의 미래를 위한 청년 육성 경진대회",
                "# 기획/아이디어 # IT/소프트웨어/게임  # 대외활동",
                "",
                "",
                "팀원 4명"
            )
        )
    )

    fun setTabPosition(position: Int) {
        tabPosition.value = position
    }
}