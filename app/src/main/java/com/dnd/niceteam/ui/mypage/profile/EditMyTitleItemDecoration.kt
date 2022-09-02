package com.dnd.niceteam.ui.mypage.profile

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dnd.niceteam.util.dpToPx

class EditMyTitleItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val spacePx =
            view.context.dpToPx(8f) / 2
        if (parent.getChildAdapterPosition(view) % 3 == 0) outRect.right = spacePx
        else if (parent.getChildAdapterPosition(view) % 3 == 2) outRect.left = spacePx
        else {
            outRect.left = spacePx
            outRect.right = spacePx
        }
    }
}