package com.dnd.niceteam.ui.mypage.myteample

import androidx.databinding.BindingAdapter
import com.dnd.niceteam.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup

@BindingAdapter("reviewTags")
fun ChipGroup.bindReviewTags(reviewTags: List<String>?) {
    reviewTags?.forEach { tag ->
        val tagView: Chip = Chip(context).apply {
            val drawable = ChipDrawable.createFromAttributes(
                context,
                null,
                0,
                R.style.Widget_Teamgoo_Chip_Choice
            )
            setChipDrawable(drawable)
            setTextAppearanceResource(R.style.TextAppearance_Teamgoo_Chip_Choice)
            text = tag
            isCheckable = true
        }
        addView(tagView)
    }
}