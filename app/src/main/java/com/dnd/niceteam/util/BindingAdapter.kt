package com.dnd.niceteam.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("adapter")
fun RecyclerView.bindAdapter(adapter: RecyclerView.Adapter<*>) {
    this.adapter = adapter
}

@BindingAdapter("itemDecoration")
fun RecyclerView.bindItemDecoration(itemDecoration: RecyclerView.ItemDecoration) {
    if (itemDecorationCount == 0) addItemDecoration(itemDecoration)
}