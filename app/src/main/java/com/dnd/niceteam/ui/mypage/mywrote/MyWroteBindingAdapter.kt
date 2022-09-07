package com.dnd.niceteam.ui.mypage.mywrote

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.dnd.niceteam.R
import com.dnd.niceteam.ui.mypage.mywrote.adapter.MyComment

@BindingAdapter("myRecruitmentStatus")
fun TextView.bindMyRecruitmentStatus(status: String) {
    when (status) {
        "모집중" -> {
            background = ContextCompat.getDrawable(context, R.drawable.bg_purple_16dp)
            text = context.getString(R.string.label_chip_recruitment)
            setTextColor(ContextCompat.getColor(context, R.color.white))
        }
        "모집완료" -> {
            background = ContextCompat.getDrawable(context, R.drawable.bg_light_purple_16dp)
            text = context.getString(R.string.label_chip_complete_recruitment)
            setTextColor(ContextCompat.getColor(context, R.color.primary_purple))
        }
        "모집실패" -> {
            background = ContextCompat.getDrawable(context, R.drawable.bg_gray_4_16dp)
            text = context.getString(R.string.label_chip_fail_recruitment)
            setTextColor(ContextCompat.getColor(context, R.color.white))
        }
        else -> return
    }
}

@BindingAdapter("myRecruitmentFirstButton")
fun AppCompatButton.bindMyRecruitmentFirstButton(status: String) {
    text = when (status) {
        "모집중" -> context.getString(R.string.label_pull_up_button)
        else -> context.getString(R.string.label_again_recruitment_button)
    }
}

@BindingAdapter("myRecruitmentSecondButton")
fun AppCompatButton.bindMyRecruitmentSecondButton(status: String) {
    visibility = when (status) {
        "모집중" -> View.VISIBLE
        else -> View.GONE
    }
}