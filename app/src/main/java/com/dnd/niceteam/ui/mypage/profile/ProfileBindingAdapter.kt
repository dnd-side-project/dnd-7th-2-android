package com.dnd.niceteam.ui.mypage.profile

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.dnd.niceteam.R

@BindingAdapter("rankImage")
fun ImageView.bindRankImage(rank: Int?) {
    when (rank) {
        1 -> {
            setImageResource(R.drawable.ic_rank_1)
            visibility = View.VISIBLE
        }
        2 -> {
            setImageResource(R.drawable.ic_rank_2)
            visibility = View.VISIBLE
        }
        3 -> {
            setImageResource(R.drawable.ic_rank_3)
            visibility = View.VISIBLE
        }
        else -> {
            setImageResource(0)
            visibility = View.INVISIBLE
        }
    }
}

@BindingAdapter("rank")
fun TextView.bindRank(rank: Int?) {
    if (rank != null && rank > 3) {
        text = rank.toString()
        visibility = View.VISIBLE
    } else visibility = View.INVISIBLE
}
