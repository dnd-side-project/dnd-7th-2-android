package com.dnd.niceteam.ui.common

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.dnd.niceteam.R
import com.dnd.niceteam.databinding.LayoutToastBinding
import com.dnd.niceteam.util.dpToPx

fun Context.teamGooToastMessage(text: String): Toast? {
    val inflater = LayoutInflater.from(this)
    val binding: LayoutToastBinding =
        DataBindingUtil.inflate(inflater, R.layout.layout_toast, null, false)
    binding.tvTitle.text = text
    return Toast(this).apply {
        setGravity(Gravity.BOTTOM, 0, dpToPx(16f))
        duration = Toast.LENGTH_SHORT
        view = binding.root
    }
}