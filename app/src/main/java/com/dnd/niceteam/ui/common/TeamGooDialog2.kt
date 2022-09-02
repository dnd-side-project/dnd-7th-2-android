package com.dnd.niceteam.ui.common

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dnd.niceteam.databinding.ViewDialog2Binding
import com.dnd.niceteam.databinding.ViewDialogBinding

class TeamGooDialog2(
    private val message: String,
    private val firstButtonText: String,
    private val secondButtonText: String,
    private val clickedFirstButton: () -> Unit,
    private val clickedSecondButton: () -> Unit
) : DialogFragment() {

    private var _binding: ViewDialog2Binding? = null
    val binding: ViewDialog2Binding
        get() = _binding ?: error("ViewBinding error")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewDialog2Binding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            tvMessage.text = message
            btnFirst.apply {
                text = firstButtonText
                setOnClickListener {
                    dismiss()
                    clickedFirstButton()
                }
            }
            btnSecond.apply {
                text = secondButtonText
                setOnClickListener {
                    dismiss()
                    clickedSecondButton()
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}