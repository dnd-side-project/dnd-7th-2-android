package com.dnd.niceteam.ui.mypage.myteample.view

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseActivity
import com.dnd.niceteam.databinding.ActivityReviewBinding
import com.dnd.niceteam.ui.common.TeamGooDialog
import com.dnd.niceteam.ui.mypage.myteample.adapter.ReviewAdapter
import com.dnd.niceteam.ui.mypage.myteample.viewmodel.ReviewViewModel
import com.dnd.niceteam.util.dpToPx
import kotlinx.coroutines.flow.collectLatest

class ReviewActivity : BaseActivity<ActivityReviewBinding>(R.layout.activity_review) {

    private val reviewViewModel: ReviewViewModel by viewModels()
    private val reviewAdapter: ReviewAdapter by lazy {
        ReviewAdapter(
            reviewViewModel,
            supportFragmentManager,
            lifecycle
        ) { position ->
            if (position != reviewViewModel.memberList.value.size)
                binding.viewPagerMember.setCurrentItem(position + 1, true)
        }
    }

    private var indicators = arrayListOf<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ReviewDialog().show(supportFragmentManager, "review_dialog")
        initView()
        observeData()
    }

    private fun initView() {
        with(binding) {
            viewPagerMember.apply {
                adapter = reviewAdapter
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        selectedIndicator(position)
                        updateNextButton(position)
                    }
                })
            }
            btnCancel.setOnClickListener {
                showStopReviewDialog()
            }
            btnNext.setOnClickListener {
                val curPos = viewPagerMember.currentItem
                val size = reviewViewModel.memberList.value.size
                if (curPos != size - 1) viewPagerMember.setCurrentItem(curPos + 1, true)
                else finish()
            }
        }
    }

    private fun observeData() {
        with(reviewViewModel) {
            lifecycleScope.launchWhenStarted {
                memberList.collectLatest {
                    val memberCount = it.size
                    reviewViewModel.initReviews(memberCount)
                    reviewAdapter.notifyDataSetChanged()
                    initIndicator(memberCount)
                }
            }
            lifecycleScope.launchWhenStarted {
                reviews.collectLatest { reviews ->
                    checkReviewComplete(reviews)
                }
            }
        }
    }

    private fun checkReviewComplete(reviews: Array<MemberReview?>) {
        with(binding) {
            val curPos = viewPagerMember.currentItem
            val size = reviewViewModel.memberList.value.size
            if (curPos == size - 1) {
                reviews.forEach {
                    val skipReview = it?.skipReview
                    val review = it?.review
                    if (skipReview != true && (review?.tags == null || review.participation == null || review.hopeTeamAgain == null)) {
                        btnNext.isEnabled = false
                        return
                    }
                }
                btnNext.isEnabled = true
            }
        }
    }

    private fun initIndicator(size: Int) {
        with(binding) {
            for (i in 0 until size) {
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    rightMargin = dpToPx(6f)
                    bottomMargin = dpToPx(6f)
                }
                val textView = TextView(
                    this@ReviewActivity,
                    null,
                    0,
                    R.style.Widget_Teamgoo_Indicator
                ).apply {
                    width = dpToPx(20f)
                    height = dpToPx(20f)
                    text = (i + 1).toString()
                    layoutParams = params
                }
                indicators.add(textView)
                layoutIndicator.addView(indicators[i])
            }
        }
    }

    private fun selectedIndicator(position: Int) {
        for (i in indicators.indices) {
            indicators[i].background =
                if (i == position) ContextCompat.getDrawable(this, R.drawable.circle_purple)
                else ContextCompat.getDrawable(this, R.drawable.circle_gray_3)
        }
    }

    private fun updateNextButton(position: Int) {
        with(binding.btnNext) {
            val size = reviewViewModel.memberList.value.size
            if (position == size - 1) {
                text = this@ReviewActivity.getString(R.string.label_submit_review_button)
                checkReviewComplete(reviewViewModel.reviews.value)
            } else {
                text = this@ReviewActivity.getString(R.string.label_next_member_button)
                isEnabled = true
            }
        }
    }

    private fun showStopReviewDialog() {
        TeamGooDialog(
            message = "작성 중인 내용이 모두 사라져요.\n그래도 페이지에서 나갈까요?",
            firstButtonText = "취소",
            secondButtonText = "나가기",
            clickedFirstButton = {},
            clickedSecondButton = {
                super.onBackPressed()
            }
        ).show(supportFragmentManager, "msg_dialog")
    }

    override fun onBackPressed() {
        showStopReviewDialog()
    }
}

data class MemberReview(
    var memberId: Int,
    var review: Review,
    var skipReview: Boolean = false
) {
    data class Review(
        var tags: ArrayList<String>?,
        var participation: Int?,
        var hopeTeamAgain: Int?,
    )
}