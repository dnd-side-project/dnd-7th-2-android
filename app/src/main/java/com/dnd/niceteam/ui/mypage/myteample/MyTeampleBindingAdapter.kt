package com.dnd.niceteam.ui.mypage.myteample

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.dnd.niceteam.R
import com.dnd.niceteam.ui.mypage.myteample.adapter.MyTeample

@BindingAdapter("myTeampleContainer")
fun ConstraintLayout.bindMyTeampleContainer(tabPosition: Int) {
    val params = layoutParams
    layoutParams.height = context.resources.getDimensionPixelSize(
        if (tabPosition == 0) R.dimen.height_my_teample_item_134dp  // 진행중 팀플
        else R.dimen.height_my_teample_item_188dp   // 완료 팀플
    )
    layoutParams = params
}

@BindingAdapter(value = ["teampleType", "projectCategory"])
fun TextView.bindMyTeampleProjectCategory(teampleType: String, projectCategory: String) {
    when (teampleType) {
        "사이드" -> {
            visibility = View.VISIBLE
            text = projectCategory
        }
        else -> {
            visibility = View.INVISIBLE
        }
    }
}

@BindingAdapter("myTeampleLectureLayout")
fun ConstraintLayout.bindMyTeampleLectureLayout(teampleType: String) {
    visibility = if (teampleType == "강의") View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("myTeampleReviewButton")
fun AppCompatButton.bindMyTeampleReviewButton(tabPosition: Int) {
    visibility = if (tabPosition == 0) View.GONE else View.VISIBLE
}

@BindingAdapter(value = ["tabPosition", "progressList"])
fun ConstraintLayout.bindMyProgressTeampleEmptyView(
    tabPosition: Int,
    progressList: List<MyTeample>
) {
    visibility = if (tabPosition == 0 && progressList.isEmpty()) View.VISIBLE else View.GONE
}

@BindingAdapter(value = ["tabPosition", "completeList"])
fun ConstraintLayout.bindMyCompleteTeampleEmptyView(
    tabPosition: Int,
    progressList: List<MyTeample>
) {
    visibility = if (tabPosition == 1 && progressList.isEmpty()) View.VISIBLE else View.GONE
}

