package com.dnd.niceteam.ui.mypage.myteample.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.viewpager2.widget.ViewPager2
import com.dnd.niceteam.databinding.ViewDialogExportBinding
import com.dnd.niceteam.ui.mypage.myteample.adapter.MemberAdapter
import com.dnd.niceteam.ui.mypage.myteample.viewmodel.MyTeampleDetailViewModel
import com.dnd.niceteam.util.dpToPx
import kotlin.math.abs

class ExportDialog(
    private val viewModel: MyTeampleDetailViewModel
) : DialogFragment() {

    private var _binding: ViewDialogExportBinding? = null
    val binding: ViewDialogExportBinding
        get() = _binding ?: error("ViewBinding error")

    private val memberAdapter: MemberAdapter by lazy {
        MemberAdapter(viewModel) { position ->
            selectExportMember(position)
        }.apply {
            submitList(viewModel.teampleDetail.value.members)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.updateExportMemberPosition(null)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewDialogExportBinding.inflate(inflater, container, false)
        initWindow()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            firstVote = viewModel.teampleDetail.value.firstVote
            btnExportFirstVote.setOnClickListener {
                dismiss()
                // 내보내기 투표요청
            }
            btnExport.setOnClickListener {
                dismiss()
                // 내보내기 찬성
            }
            btnCancelFirstVote.setOnClickListener {
                dismiss()
            }
            btnCancel.setOnClickListener {
                dismiss()
                // 내보내기 거절
            }
            initMemberViewPager(viewPagerMember)
        }
    }

    private fun initWindow() {
        val params = dialog?.window?.attributes
        params?.apply {
            width = (resources.displayMetrics.widthPixels * 0.88).toInt()
            height = WindowManager.LayoutParams.WRAP_CONTENT
        }
        dialog?.window?.attributes = params
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun initMemberViewPager(viewPager: ViewPager2) {
        val pageMarginPx = requireContext().dpToPx(8f)
        val pagerWidth = requireContext().dpToPx(128f)
        val screenWidth = requireContext().dpToPx(304f)
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        viewPager.apply {
            adapter = memberAdapter
            offscreenPageLimit = 10

            setPageTransformer { page, position ->
                val alphaValue = 1 - (0.5f * abs(position))
                page.alpha = alphaValue
                page.translationX = position * -offsetPx
            }
        }
    }

    private fun selectExportMember(position: Int) {
        with(binding) {
            viewPagerMember.setCurrentItem(position, true)
            btnExportFirstVote.isEnabled = viewModel.exportMemberPosition.value != null
        }
    }
}