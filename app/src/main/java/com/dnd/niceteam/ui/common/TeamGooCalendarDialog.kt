package com.dnd.niceteam.ui.common

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.dnd.niceteam.R
import com.dnd.niceteam.databinding.ViewDialogCalendarBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.MaterialCalendarView.SELECTION_MODE_SINGLE

/**
 * @description         : 커스텀 달력 다이얼로그
 *                        → 외부 라이브러리 사용(material-calendarview)
 *                        → 모집글의 마감기한 및 프로젝트 기간설정, 끌올 마감기간에 이용
 *                        → DayViewDecorator를 이용해 단일 및 기간 선택 표현
 *                        (라이브러리 링크 : https://github.com/prolificinteractive/material-calendarview)
 *
 * @param title         : 다이얼로그 타이틀
 * @param checkBoxTitle : 하단 checkbox 타이틀
 * @param selectionMode : 달력모드 → 1.단일(SELECTION_MODE_SINGLE) 2.기간(SELECTION_MODE_RANGE)
 * @param confirmed     : 확인버튼 클릭 이벤트
 *
 * @since 2022.09.01
 */
class TeamGooCalendarDialog(
    private val title: String,
    private val checkBoxTitle: String,
    private val selectionMode: Int,
    private val confirmed: () -> Unit
) : BottomSheetDialogFragment() {

    private var _binding: ViewDialogCalendarBinding? = null
    val binding: ViewDialogCalendarBinding
        get() = _binding ?: error("ViewBinding error")

    override fun getTheme(): Int = R.style.Theme_Teamgoo_BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewDialogCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomSheet =
            dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        val behavior = BottomSheetBehavior.from<View>(bottomSheet!!)
        behavior.skipCollapsed = true
        behavior.state = BottomSheetBehavior.STATE_EXPANDED

        initView()
    }

    private fun initView() {
        with(binding) {
            initCalendarView(calendarView)
            tvTitle.text = title
            cbNoLimit.apply {
                text = checkBoxTitle
                setOnCheckedChangeListener { _, checked ->
                    if (checked) checkNoLimit()
                }
            }
            btnConfirm.setOnClickListener {
                dismiss()
                confirmed()
            }
        }
    }

    private fun initCalendarView(calendar: MaterialCalendarView) {
        calendar.apply {
            selectionMode = this@TeamGooCalendarDialog.selectionMode
            addDecorator(TodayDecorator())

            setOnRangeSelectedListener { _, dates ->
                binding.btnConfirm.isEnabled = true
                addDecorators(
                    RangeDecorator(dates),
                    RangeFirstDecorator(dates),
                    RangeLastDecorator(dates)
                )
            }

            setOnDateChangedListener { _, _, selected ->
                binding.cbNoLimit.isChecked = false
                if (selectionMode == SELECTION_MODE_SINGLE) binding.btnConfirm.isEnabled = selected
                else binding.btnConfirm.isEnabled = false
                removeDecorators()
                addDecorator(TodayDecorator())
                addDecorator(
                    EventDecorator(
                        calendar.selectedDate ?: return@setOnDateChangedListener
                    )
                )
            }
        }
    }

    private fun checkNoLimit() {
        // 기간제한없음 checkbox 선택시 → 버튼활성화, 날짜초기화
        with(binding) {
            btnConfirm.isEnabled = true
            calendarView.apply {
                clearSelection()
                removeDecorators()
                addDecorator(TodayDecorator())
            }
        }
    }

    inner class TodayDecorator : DayViewDecorator {
        override fun shouldDecorate(day: CalendarDay?): Boolean {
            return day?.equals(CalendarDay.today())!!
        }

        override fun decorate(view: DayViewFacade?) {
            view?.apply {
                addSpan(ForegroundColorSpan(Color.parseColor("#7165FF")))
                setSelectionDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.circle_gray_2
                    ) ?: return
                )
            }
        }
    }

    inner class EventDecorator(private val date: CalendarDay) : DayViewDecorator {
        override fun shouldDecorate(day: CalendarDay?): Boolean {
            return day?.equals(date)!!
        }

        override fun decorate(view: DayViewFacade?) {
            view?.apply {
                addSpan(ForegroundColorSpan(Color.parseColor("#FFFFFF")))
                setSelectionDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.circle_purple
                    ) ?: return
                )
            }
        }
    }

    inner class RangeDecorator(private val date: List<CalendarDay>) : DayViewDecorator {
        override fun shouldDecorate(day: CalendarDay?): Boolean {
            return day!!.isInRange(date.first(), date.last())
        }

        override fun decorate(view: DayViewFacade?) {
            view?.apply {
                addSpan(ForegroundColorSpan(Color.parseColor("#7165FF")))
                setSelectionDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.bg_light_purple
                    ) ?: return
                )
            }
        }
    }

    inner class RangeLastDecorator(private val date: List<CalendarDay>) : DayViewDecorator {
        override fun shouldDecorate(day: CalendarDay?): Boolean {
            return day?.equals(date.last())!!
        }

        override fun decorate(view: DayViewFacade?) {
            view?.apply {
                addSpan(ForegroundColorSpan(Color.parseColor("#FFFFFF")))
                setSelectionDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.bg_calendar_decorator_last_range
                    ) ?: return
                )
            }
        }
    }

    inner class RangeFirstDecorator(private val date: List<CalendarDay>) :
        DayViewDecorator {
        override fun shouldDecorate(day: CalendarDay?): Boolean {
            return day?.equals(date.first())!!
        }

        override fun decorate(view: DayViewFacade?) {
            view?.apply {
                addSpan(ForegroundColorSpan(Color.parseColor("#FFFFFF")))
                setSelectionDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.bg_calendar_decorator_first_range
                    ) ?: return
                )
            }
        }
    }
}
