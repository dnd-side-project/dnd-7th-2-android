package com.dnd.niceteam.ui.mypage.myteample.view

import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseFragment
import com.dnd.niceteam.databinding.FragmentReviewBinding
import com.dnd.niceteam.ui.mypage.myteample.viewmodel.ReviewViewModel
import com.google.android.material.chip.Chip

class ReviewFragment(
    private val reviewViewModel: ReviewViewModel,
    private val position: Int,
    private val clickedSkip: (position: Int) -> Unit
) : BaseFragment<FragmentReviewBinding>(R.layout.fragment_review),
    RatingBar.OnRatingBarChangeListener {

    private val member = reviewViewModel.memberList.value[position]

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        initView()
    }

    private fun bind() {
        with(binding) {
            viewModel = this@ReviewFragment.reviewViewModel
            member = this@ReviewFragment.member
        }
    }

    private fun initView() {
        with(binding) {
            chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
                val tags = if (checkedIds.isNotEmpty()) {
                    val tagList = arrayListOf<String>()
                    checkedIds.forEach {
                        tagList.add(chipGroup.findViewById<Chip>(it).text.toString())
                    }
                    tagList
                } else null
                reviewViewModel.updateReviewTags(position, tags)
            }

            ratingBarParticipation.onRatingBarChangeListener = this@ReviewFragment
            ratingBarHopeTeamAgain.onRatingBarChangeListener = this@ReviewFragment

            btnSkip.setOnClickListener {
                updateCancelSkipLayout(true)
                reviewViewModel.updateSkipReview(position, true)
                clickedSkip(position)
            }

            btnCancelSkip.setOnClickListener {
                updateCancelSkipLayout(false)
                reviewViewModel.updateSkipReview(position, false)
            }

            updateCancelSkipLayout(reviewViewModel.reviews.value.getOrNull(position)?.skipReview)
        }
    }

    private fun updateCancelSkipLayout(skip: Boolean?) {
        binding.layoutCancelSkip.visibility = if (skip == true) View.VISIBLE else View.GONE
    }

    override fun onRatingChanged(ratingBar: RatingBar?, f: Float, b: Boolean) {
        ratingBar?.let { rb ->
            when (rb.rating) {
                in 0f..1f -> rb.rating = 1f
                in 1.1f..2f -> rb.rating = 2f
                in 2.1f..3f -> rb.rating = 3f
                in 3.1f..4f -> rb.rating = 4f
                in 4.1f..5f -> rb.rating = 5f
            }
            if (rb.id == R.id.rating_bar_participation) {
                reviewViewModel.updateParticipationRating(position, rb.rating.toInt())
            } else if (rb.id == R.id.rating_bar_hope_team_again) {
                reviewViewModel.updateHopeTeamAgainRating(position, rb.rating.toInt())
            }
        }
    }
}