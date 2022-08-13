package com.dnd.niceteam.ui.home.alarm.viewmodel

import androidx.lifecycle.ViewModel
import com.dnd.niceteam.ui.home.alarm.adapter.Model
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor() : ViewModel() {

    var alarmList = MutableStateFlow<List<Model>>(
        listOf(
            Model(
                0,
                "(작성한 모집글 제목)에 댓글이 달렸어요.",
                "안녕하세요! 프로젝트 난이도는 많이 어려운가요? 제가 프로젝트 경험이 없어서..",
                "1일 전"
            ),
            Model(
                1,
                "내가 쓴댓글에 답글이 달렸어요.",
                "안녕하세요! 가나다라마바사",
                "1일 전"
            ),
            Model(
                2,
                "(작성한 모집글 제목)에 닉네임님이 지원했어요.",
                "지금 바로 확인해보세요 :)",
                "1일 전"
            ),
            Model(
                3,
                "지원하신 (작성한 모집글 제목)의 팀원으로 초대 받았어요!",
                "모집이 완료되면 본격적으로 팀플을 시작할 수 있어요!",
                "1일 전"
            ),
            Model(
                4,
                "(작성한 모집글 제목)를 함께한 팀원에 대해 후기를 남겨보세요!",
                "후기를 쌓일수록 신뢰도가 올라가요 :)",
                "1일 전"
            ),
            Model(
                5,
                "(작성한 모집글 제목)에 댓글이 달렸어요.",
                "안녕하세요! 프로젝트 난이도는 많이 어려운가요? 제가 프로젝트 경험이 없어서..",
                "1일 전"
            ),
            Model(
                6,
                "내가 쓴댓글에 답글이 달렸어요.",
                "안녕하세요! 가나다라마바사",
                "1일 전"
            ),
            Model(
                7,
                "(작성한 모집글 제목)에 닉네임님이 지원했어요.",
                "지금 바로 확인해보세요 :)",
                "1일 전"
            )
        )
    )
    var deleteList = MutableStateFlow<List<Int>>(listOf())
    var deleteMode = MutableStateFlow<Boolean>(false)

    fun changeDeleteMode() {
        deleteList.value = listOf()
        deleteMode.value = !deleteMode.value
    }

    fun deleteAlarm() {
        val list = mutableListOf<Model>()
        alarmList.value.forEachIndexed { i, model ->
            if (deleteList.value.isEmpty()) return
            else if (!deleteList.value.contains(i)) list.add(model)
        }
        alarmList.value = list
    }
}