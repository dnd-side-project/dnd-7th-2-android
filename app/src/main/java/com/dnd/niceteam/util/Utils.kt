package com.dnd.niceteam.util

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager
import android.view.WindowMetrics

fun Context.getScreenSize(): Point {
    val size = Point()
    val windowManager = (getSystemService(Context.WINDOW_SERVICE)) as WindowManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics: WindowMetrics = windowManager.currentWindowMetrics
        size.apply {
            x = windowMetrics.bounds.width()
            y = windowMetrics.bounds.height()
        }
    } else {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        size.apply {
            x = displayMetrics.widthPixels
            y = displayMetrics.heightPixels
        }
    }
}