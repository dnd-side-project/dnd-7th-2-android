package com.dnd.niceteam.ui.mypage.myteample.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnd.niceteam.ui.mypage.myteample.view.Member
import com.dnd.niceteam.ui.mypage.myteample.view.MyTeampleDetailActivity.Companion.COMPLETE_TEAMPLE
import com.dnd.niceteam.ui.mypage.myteample.view.TeampleDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyTeampleDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val isCompleteTeample: Boolean = savedStateHandle[COMPLETE_TEAMPLE]
        ?: throw IllegalStateException("Intent error")

    var teampleDetail = MutableStateFlow(
        TeampleDetail(
            1,
            "팀구은행 핀테크 산업의 미래를 위한 청년 육성 경진대회",
            "공모전",
            "기획/아이디어  |  IT/소프트웨어/게임",
            listOf(
                Member(1, 1, "내프로필", "논리적인 팔로워"),
                Member(2, 2, "고구마감자와당근", "목표 지향적 커뮤니케이터", "Y"),
                Member(3, 3, "냥냥냥냥내옹", "쾌활한 리더"),
                Member(4, 4, "백세인생백수", "신속한 발명가"),
                Member(5, 3, "씰룩홈즈", "성실한 중재자"),
                Member(6, 1, "응애나아가", "쾌활한 모험가")
            ),
            "2022.09.26 - 2022.10.21",
            "온라인",
            true
        )
    )

    private var _exportMemberPosition = MutableStateFlow<Int?>(null)
    val exportMemberPosition = _exportMemberPosition.asStateFlow()

    fun updateExportMemberPosition(position: Int?) {
        _exportMemberPosition.value = position
    }
}