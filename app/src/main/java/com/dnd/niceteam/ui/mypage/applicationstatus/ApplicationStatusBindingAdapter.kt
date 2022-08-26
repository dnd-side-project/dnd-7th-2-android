package com.dnd.niceteam.ui.mypage.applicationstatus

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.dnd.niceteam.R
import com.dnd.niceteam.ui.mypage.applicationstatus.view.ApplicationStatusActivity.Companion.STATUS_ACCEPT
import com.dnd.niceteam.ui.mypage.applicationstatus.view.ApplicationStatusActivity.Companion.STATUS_APPLY
import com.dnd.niceteam.ui.mypage.applicationstatus.view.ApplicationStatusActivity.Companion.STATUS_END_RECRUITMENT

@BindingAdapter("applicationStatusTag")
fun TextView.bindApplicationStatusTag(type: String?) {
    when (type) {
        STATUS_APPLY -> {
            background = ContextCompat.getDrawable(context, R.drawable.bg_purple_16dp)
            text = context.getString(R.string.label_chip_apply)
            setTextColor(ContextCompat.getColor(context, R.color.white))
        }
        STATUS_ACCEPT -> {
            background = ContextCompat.getDrawable(context, R.drawable.bg_light_purple_16dp)
            text = context.getString(R.string.label_chip_accept)
            setTextColor(ContextCompat.getColor(context, R.color.primary_purple))
        }
        STATUS_END_RECRUITMENT -> {
            background = ContextCompat.getDrawable(context, R.drawable.bg_gray_16dp)
            text = context.getString(R.string.label_chip_end_recruitment)
            setTextColor(ContextCompat.getColor(context, R.color.white))
        }
        else -> return
    }
}
