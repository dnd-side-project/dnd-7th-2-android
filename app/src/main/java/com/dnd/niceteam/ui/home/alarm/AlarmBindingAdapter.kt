package com.dnd.niceteam.ui.home.alarm

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.BindingAdapter

@BindingAdapter("showAlarmSelectAll")
fun TextView.bindShowSelectAll(deleteMode: Boolean) {
    visibility = if (deleteMode) View.VISIBLE else View.GONE
}

@BindingAdapter("showAlarmDeleteButton")
fun AppCompatButton.bindShowAlarmDeleteButton(deleteList: List<Int>) {
    visibility = if (deleteList.isNotEmpty()) View.VISIBLE else View.GONE
}