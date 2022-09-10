package com.dnd.niceteam.ui.mypage.myteample

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dnd.niceteam.util.dpToPx

class MemberSmallItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) != state.itemCount - 1)
            outRect.right = parent.context.dpToPx(8f)
    }
}