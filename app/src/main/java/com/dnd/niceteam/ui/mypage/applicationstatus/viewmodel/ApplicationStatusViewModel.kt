package com.dnd.niceteam.ui.mypage.applicationstatus.viewmodel

import androidx.lifecycle.ViewModel
import com.dnd.niceteam.ui.mypage.applicationstatus.adapter.Model
import com.dnd.niceteam.ui.mypage.applicationstatus.view.ApplicationStatusActivity.Companion.STATUS_ALL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ApplicationStatusViewModel @Inject constructor() : ViewModel() {

    private val allList = MutableStateFlow<List<Model>>(
        listOf(
            Model(1, "입력된 사이드 프로젝트명 / 강의명", "4명 모집", "수락"),
            Model(2, "입력된 사이드 프로젝트명 / 강의명", "4명 모집", "지원중"),
            Model(3, "입력된 사이드 프로젝트명 / 강의명", "4명 모집", "지원중"),
            Model(4, "입력된 사이드 프로젝트명 / 강의명", "4명 모집", "수락"),
            Model(5, "입력된 사이드 프로젝트명 / 강의명", "4명 모집", "지원중")
        )
    )
    val currentList = MutableStateFlow<List<Model>>(listOf())

    fun listFiltering(type: String): List<Model> {
        val filterList =
            if (type == STATUS_ALL) allList.value
            else allList.value.filter { it.type == type }
        currentList.value = filterList
        return filterList
    }
}