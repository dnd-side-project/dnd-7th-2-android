package com.dnd.niceteam.ui.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.dnd.niceteam.R
import com.dnd.niceteam.databinding.ViewAppbarBinding

class TeamGooAppBar(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: ViewAppbarBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_appbar, this, true)

    private val title = binding.tvTitle
    private val navigationIcon = binding.ivNavigationIcon
    private val firstAction = binding.ivFirstAction
    private val secondAction = binding.ivSecondAction

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
                setTitle(getString(R.styleable.TeamGooAppBar_title))
                setNavigationIcon(getResourceId(R.styleable.TeamGooAppBar_icon_navigation, 0))
                setFirstAction(getResourceId(R.styleable.TeamGooAppBar_icon_first_action, 0))
                setSecondAction(getResourceId(R.styleable.TeamGooAppBar_icon_second_action, 0))
                setHomeAppBar(getBoolean(R.styleable.TeamGooAppBar_isHomeAppBar, false))
            } finally {
                recycle()
            }
        }
    }

    private fun setTitle(title: String?) {
        if (title != null) this.title.text = title
    }

    private fun setNavigationIcon(resourceId: Int) {
        if (resourceId != 0) navigationIcon.setImageResource(resourceId)
    }

    fun setFirstAction(resourceId: Int) {
        if (resourceId != 0) firstAction.setImageResource(resourceId)
        else firstAction.isVisible = isGone
    }

    private fun setSecondAction(resourceId: Int) {
        if (resourceId != 0) secondAction.setImageResource(resourceId)
        else secondAction.isVisible = isGone
    }

    private fun setHomeAppBar(flag: Boolean) {
        title.setTextAppearance(
            if (flag) R.style.TextAppearance_Teamgoo_AppBar_Title_Purple
            else R.style.TextAppearance_Teamgoo_AppBar_Title
        )
    }

    fun clickedNavigationIcon(clicked: () -> Unit) = navigationIcon.setOnClickListener { clicked() }

    fun clickedFirstAction(clicked: () -> Unit) = firstAction.setOnClickListener { clicked() }

    fun clickedSecondAction(clicked: () -> Unit) = secondAction.setOnClickListener { clicked() }
}
