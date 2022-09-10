package com.dnd.niceteam.ui.mypage.myteample

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.dnd.niceteam.R
import com.dnd.niceteam.ui.mypage.myteample.view.Member
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup

@BindingAdapter("myTeampleDetailPersonnel")
fun TextView.bindMyTeampleDetailPersonnel(members: List<Member>?) {
    text = "${context.getString(R.string.label_member)} ${members?.size}"
}

@BindingAdapter("memberTitleTags")
fun ChipGroup.bindMemberTitleTags(members: List<Member>?) {
    members?.forEach { member ->
        val tagView: Chip = Chip(context).apply {
            val drawable = ChipDrawable.createFromAttributes(
                context,
                null,
                0,
                R.style.Widget_Teamgoo_Chip_Gray_34dp
            )
            setChipDrawable(drawable)
            setTextAppearanceResource(R.style.TextAppearance_Teamgoo_Chip_34dp)
            isClickable = false
            text = member.title
        }
        addView(tagView)
    }
}

@BindingAdapter("memberProfileImage")
fun ImageView.bindMemberProfileImage(level: Int) {
    setImageResource(
        when (level) {
            1 -> R.drawable.ic_level_1_member_profile
            2 -> R.drawable.ic_level_2_member_profile
            3 -> R.drawable.ic_level_3_member_profile
            4 -> R.drawable.ic_level_4_member_profile
            else -> R.drawable.ic_level_1_member_profile
        }
    )
}

@BindingAdapter("recruiterTag")
fun TextView.bindRecruiterTag(recruiterYn: String) {
    visibility = if (recruiterYn == "Y") View.VISIBLE else View.GONE
}

@BindingAdapter("exportButtonLayout")
fun ConstraintLayout.bindExportButtonLayout(firstVote: Boolean) {
    visibility = if (firstVote) View.VISIBLE else View.GONE
}