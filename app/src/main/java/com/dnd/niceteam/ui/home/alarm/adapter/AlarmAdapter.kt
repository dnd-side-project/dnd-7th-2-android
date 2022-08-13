package com.dnd.niceteam.ui.home.alarm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnd.niceteam.databinding.ItemAlarmBinding
import com.dnd.niceteam.ui.home.alarm.viewmodel.AlarmViewModel

class AlarmAdapter(private val alarmViewModel: AlarmViewModel) :
    ListAdapter<Model, AlarmAdapter.AlarmViewHolder>(diffCallback) {

    init {
        setHasStableIds(true)
    }

    fun selectAll() {
        val selectedAllList = arrayListOf<Int>()
        for (i in 0 until alarmViewModel.alarmList.value.size) {
            selectedAllList.add(i)
        }
        alarmViewModel.deleteList.value = selectedAllList
        notifyDataSetChanged()
    }

    inner class AlarmViewHolder(val binding: ItemAlarmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            with(binding) {
                layoutContainer.setOnClickListener {
                    if (this@AlarmAdapter.alarmViewModel.deleteMode.value) {
                        val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                            ?: return@setOnClickListener
                        val deleteList =
                            this@AlarmAdapter.alarmViewModel.deleteList.value.toMutableList()
                        cbDeleteAlarm.isChecked = !cbDeleteAlarm.isChecked
                        if (cbDeleteAlarm.isChecked) deleteList.add(position)
                        else deleteList.remove(position)
                        this@AlarmAdapter.alarmViewModel.deleteList.value = deleteList
                    }
                }
            }
        }

        fun bind(position: Int) {
            with(binding) {
                alarm = getItem(position)
                cbDeleteAlarm.apply {
                    isChecked = alarmViewModel.deleteList.value.contains(position)
                    visibility = if (alarmViewModel.deleteMode.value) View.VISIBLE else View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val binding = ItemAlarmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlarmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id.toLong()
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Model>() {
            override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean =
                oldItem == newItem
        }
    }
}

data class Model(
    val id: Int,
    val title: String,
    val detail: String,
    val date: String
)