package com.dnd.niceteam.util

import android.graphics.Color
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnd.niceteam.ui.common.TeamGooDecoration

@BindingAdapter("adapter")
fun RecyclerView.bindAdapter(adapter: RecyclerView.Adapter<*>) {
    this.adapter = adapter
}

@BindingAdapter("itemDecoration")
fun RecyclerView.bindItemDecoration(itemDecoration: RecyclerView.ItemDecoration) {
    if (itemDecorationCount == 0) addItemDecoration(itemDecoration)
}

@BindingAdapter(value = ["dividerHeight", "dividerPadding", "dividerColor"], requireAll = false)
fun RecyclerView.bindDivider(dividerHeight: Float?, dividerPadding: Float?, dividerColor: Int?) {
    val divider = TeamGooDecoration(
        height = dividerHeight ?: 0f,
        padding = dividerPadding ?: 0f,
        color = dividerColor ?: Color.TRANSPARENT
    )
    addItemDecoration(divider)
}

@BindingAdapter("showEmptyView")
fun ConstraintLayout.bindShowEmptyView(list: List<*>) {
    visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
}