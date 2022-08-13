package com.dnd.niceteam.ui.home.alarm.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseActivity
import com.dnd.niceteam.databinding.ActivityAlarmBinding
import com.dnd.niceteam.ui.home.alarm.adapter.AlarmAdapter
import com.dnd.niceteam.ui.home.alarm.viewmodel.AlarmViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlarmActivity : BaseActivity<ActivityAlarmBinding>(R.layout.activity_alarm) {

    private val alarmViewModel: AlarmViewModel by viewModels()
    private val alarmAdapter: AlarmAdapter by lazy { AlarmAdapter(alarmViewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        initView()
        observeData()
    }

    private fun bind() {
        with(binding) {
            alarmViewModel = this@AlarmActivity.alarmViewModel
            alarmAdapter = this@AlarmActivity.alarmAdapter
        }
    }

    private fun initView() {
        with(binding) {
            toolbar.apply {
                clickedNavigationIcon { finish() }
                clickedFirstAction { this@AlarmActivity.alarmViewModel.changeDeleteMode() }
            }
            tvSelectAll.setOnClickListener {
                this@AlarmActivity.alarmAdapter.selectAll()
            }
            btnDeleteAlarm.setOnClickListener {
                this@AlarmActivity.alarmViewModel.apply {
                    deleteAlarm()
                    changeDeleteMode()
                }
            }
        }
    }

    private fun observeData() {
        with(alarmViewModel) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    alarmList.collectLatest {
                        alarmAdapter.submitList(it)
                        binding.toolbar.setFirstAction(if (it.isEmpty()) 0 else R.drawable.ic_trash)
                    }
                }
            }
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    deleteMode.collectLatest {
                        alarmAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}