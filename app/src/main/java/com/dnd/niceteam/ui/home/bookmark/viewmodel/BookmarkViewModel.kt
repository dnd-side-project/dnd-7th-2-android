package com.dnd.niceteam.ui.home.bookmark.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor() : ViewModel() {

    var bookmarkType = MutableStateFlow<Int>(MY_LECTURE)
    var bookmarkList = MutableStateFlow<List<Int>>(listOf())

    fun setBookmarkType(type: Int) {
        bookmarkType.value = type
        updateBookmarkList(type)
    }

    fun updateBookmarkList(type: Int) {
        bookmarkList.value = if (type == MY_LECTURE) listOf(0, 1) else listOf()
    }

    companion object {
        const val MY_LECTURE = 0
        const val SIDE = 1
    }
}