package com.dnd.niceteam.ui.home.homescreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnd.niceteam.databinding.ItemProgressTeampleBinding

class ProgressTeampleAdapter :
    ListAdapter<Int, ProgressTeampleAdapter.ProgressTeamplViewHolder>(diffCallback) {
    inner class ProgressTeamplViewHolder(private val binding: ItemProgressTeampleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            with(binding) {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressTeamplViewHolder {
        val binding =
            ItemProgressTeampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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