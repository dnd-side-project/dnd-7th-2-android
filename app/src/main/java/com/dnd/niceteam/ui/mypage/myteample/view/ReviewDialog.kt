package com.dnd.niceteam.ui.mypage.myteample.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dnd.niceteam.databinding.ViewDialogReviewBinding

class ReviewDialog : DialogFragment() {

    private var _binding: ViewDialogReviewBinding? = null
    val binding: ViewDialogReviewBinding
        get() = _binding ?: error("ViewBinding error")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewDialogReviewBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        isCancelable = true

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.layoutContainer.setOnClickListener { dismiss() }
    }
}