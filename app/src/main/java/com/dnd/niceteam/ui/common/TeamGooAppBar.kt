package com.dnd.niceteam.ui.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.dnd.niceteam.R
import com.dnd.niceteam.databinding.ViewAppbarBinding

class TeamGooAppBar(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: ViewAppbarBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_appbar, this, true)

    private val title = binding.tvTitle
    private val navigationIcon = binding.ivNavigationIcon
    private val actionIcon = binding.ivActionIcon

    init {
        initAttrs(attrs)
    }

    private fun initAttrs(attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TeamGooAppBar,
            0,
            0
        ).apply {
            try {
                setTitle(getResourceId(R.styleable.TeamGooAppBar_title, 0))
                setNavigationIcon(getResourceId(R.styleable.TeamGooAppBar_icon_navigation, 0))
                setActionIcon(getResourceId(R.styleable.TeamGooAppBar_icon_navigation, 0))
            } finally {
                recycle()
            }
        }
    }


    private fun setTitle(resourceId: Int) {
        if (resourceId != 0) title.setText(resourceId)
    }

    private fun setNavigationIcon(resourceId: Int) {
        if (resourceId != 0) navigationIcon.setImageResource(resourceId)
    }

    private fun setActionIcon(resourceId: Int) {
        if (resourceId != 0) actionIcon.setImageResource(resourceId)
    }
}
