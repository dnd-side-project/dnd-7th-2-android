package com.dnd.niceteam.ui.home.alarm

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.BindingAdapter

@BindingAdapter("alarmTopButton")
fun AppCompatButton.bindShowSelectAll(deleteMode: Boolean) {
    visibility = if (deleteMode) View.VISIBLE else View.GONE
}

@BindingAdapter("alarmDeleteButton")
fun AppCompatButton.bindShowAlarmDeleteButton(deleteList: List<Int>) {
    visibility = if (deleteList.isNotEmpty()) View.VISIBLE else View.GONE
}