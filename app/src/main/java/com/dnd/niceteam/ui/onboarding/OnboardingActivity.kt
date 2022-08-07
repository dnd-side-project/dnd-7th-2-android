package com.dnd.niceteam.ui.onboarding

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dnd.niceteam.R
import com.dnd.niceteam.databinding.ActivityOnboardingBinding
import com.dnd.niceteam.util.hideKeyboard
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)

    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val view = currentFocus

        if (view != null && ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_MOVE
            && view is TextInputEditText && !view.javaClass.name.startsWith("android.webkit.")
        ) {
            val locationList = IntArray(2)
            view.getLocationOnScreen(locationList)
            val x = ev.rawX + view.left - locationList[0]
            val y = ev.rawY + view.top - locationList[1]
            if (x < view.left || x > view.right || y < view.top || y > view.bottom) {
                hideKeyboard(view)
                view.clearFocus()
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()
    }
}