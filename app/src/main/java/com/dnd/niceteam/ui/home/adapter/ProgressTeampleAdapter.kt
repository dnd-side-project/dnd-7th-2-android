package com.dnd.niceteam.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnd.niceteam.databinding.ItemProgressTeamplBinding
import com.dnd.niceteam.util.getScreenSize

class ProgressTeampleAdapter :
    ListAdapter<Int, ProgressTeampleAdapter.ProgressTeamplViewHolder>(diffCallback) {
    inner class ProgressTeamplViewHolder(private val binding: ItemProgressTeamplBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            with(binding) {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressTeamplViewHolder {
        val binding =
            ItemProgressTeamplBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.root.layoutParams = RecyclerView.LayoutParams(
            parent.context.getScreenSize().x * 4 / 5,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        return ProgressTeamplViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProgressTeamplViewHolder, position: Int) {
        holder.bind()
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Int>() {
            override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean =
                oldItem == newItem
        }
    }
}