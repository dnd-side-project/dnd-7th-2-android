package com.dnd.niceteam.ui.mypage.applicationstatus

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dnd.niceteam.util.dpToPx

class ApplicationStatusItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val spacePx =
            view.context.dpToPx(8f) / 2    // between space = left space + right space
        if (parent.getChildAdapterPosition(view) % 2 != 0) outRect.left = spacePx
        else outRect.right = spacePx
    }
}