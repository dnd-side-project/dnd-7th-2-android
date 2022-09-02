package com.dnd.niceteam.ui.mypage.profile

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.dnd.niceteam.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup

@BindingAdapter(value = ["allInterestTags", "selectedInterestTags"])
fun ChipGroup.bindTags(allTag: List<String>?, selectedTags: List<String>?) {
    allTag?.forEach { tag ->
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
            isChecked = selectedTags?.contains(tag) ?: false
        }
        addView(tagView)
    }
}

@BindingAdapter("editMyTitleBackground")
fun ConstraintLayout.bindEditMyTitleBackground(isSelected: Boolean?) {
    background =
        if (isSelected == true) ContextCompat.getDrawable(
            context,
            R.drawable.bg_light_purple_stroke_purple_16dp
        )
        else ContextCompat.getDrawable(context, R.drawable.bg_gray_1_16dp)
}

@BindingAdapter("editMyTitleCheckIcon")
fun ImageView.bindEditMyTitleCheckIcon(isSelected: Boolean?) {
    visibility = if (isSelected == true) View.VISIBLE else View.GONE
}