package com.dnd.niceteam.ui.mypage.mypagescreen.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor() : ViewModel() {

    val experienceCount = MutableStateFlow<Int>(2)
}