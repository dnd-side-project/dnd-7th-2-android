package com.dnd.niceteam.ui.mypage.profile.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.dnd.niceteam.R
import com.dnd.niceteam.databinding.ViewDialogEditMyTitleBinding
import com.dnd.niceteam.ui.mypage.profile.EditMyTitleItemDecoration
import com.dnd.niceteam.ui.mypage.profile.adapter.EditMyTitleAdapter
import com.dnd.niceteam.ui.mypage.profile.viewmodel.EditProfileViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EditMyTitleDialog(
    private val type: String,
    private val editProfileViewModel: EditProfileViewModel
) : BottomSheetDialogFragment() {

    private var _binding: ViewDialogEditMyTitleBinding? = null
    val binding: ViewDialogEditMyTitleBinding
        get() = _binding ?: error("ViewBinding error")

    private val editMyTitleItemDecoration: EditMyTitleItemDecoration by lazy { EditMyTitleItemDecoration() }
    private val editMyTitleAdapter: EditMyTitleAdapter by lazy {
        EditMyTitleAdapter(
            if (type == ADJECTIVE) editProfileViewModel.selectedAdjectiveTitle.value
            else editProfileViewModel.selectedNounTitle.value
        ) { item ->
            dismiss()
            selectTitle(item.title)
        }
    }

    override fun getTheme(): Int = R.style.Theme_Teamgoo_BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewDialogEditMyTitleBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.itemDecoration = editMyTitleItemDecoration
        binding.adapter = editMyTitleAdapter

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            setTitleText(tvTitle)
        }
        editMyTitleAdapter.submitList(
            if (type == ADJECTIVE) editProfileViewModel.allAdjectiveTitleList.value
            else editProfileViewModel.allNounTitleList.value
        )
    }

    private fun setTitleText(title: TextView) {
        val gray =
            Integer.toHexString(ContextCompat.getColor(requireContext(), R.color.gray_6))
                .removeRange(0, 2)
        val purple =
            Integer.toHexString(ContextCompat.getColor(requireContext(), R.color.primary_purple))
                .removeRange(0, 2)
        val text1 = "<font color=#$gray>나를 표현하는 </font>"
        val text2 =
            if (type == ADJECTIVE) "<font color=#$purple>형용사 1개</font>"
            else "<font color=#$purple>명사 1개</font>"
        val text3 = "<font color=#$gray>를 선택해주세요.</font>"

        title.text = HtmlCompat.fromHtml(text1 + text2 + text3, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    private fun selectTitle(item: String) {
        if (type == ADJECTIVE) editProfileViewModel.selectAdjectiveTitle(item)
        else editProfileViewModel.selectNounTitle(item)
    }

    companion object {
        const val ADJECTIVE = "adjective"
        const val NOUN = "noun"
    }
}