package com.dnd.niceteam.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnd.niceteam.databinding.ItemProgressTeampleBinding
import com.dnd.niceteam.databinding.ItemRecommendTempleBinding
import com.dnd.niceteam.util.getScreenSize

class RecommendTeampleAdapter :
    ListAdapter<Int, RecommendTeampleAdapter.RecommendTeampleViewHolder>(diffCallback) {

    inner class RecommendTeampleViewHolder(private val binding: ItemRecommendTempleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            with(binding) {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendTeampleViewHolder {
        val binding =
            ItemRecommendTempleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendTeampleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendTeampleViewHolder, position: Int) {
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