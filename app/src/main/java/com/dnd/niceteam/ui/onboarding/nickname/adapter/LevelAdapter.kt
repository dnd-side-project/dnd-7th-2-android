package com.dnd.niceteam.ui.onboarding.nickname.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnd.niceteam.databinding.ItemLevelBinding

class LevelAdapter(
    animation: Animation,
    private val setImage: (ImageView) -> Unit
) : ListAdapter<String, LevelAdapter.LevelViewModel>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewModel {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLevelBinding.inflate(inflater, parent, false)
        return LevelViewModel(binding, setImage)
    }

    override fun onBindViewHolder(holder: LevelViewModel, position: Int) {
        holder.bind(getItem(position), position)
    }

    class LevelViewModel(
        private val binding: ItemLevelBinding,
        private val setImage: (ImageView) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(level: String, position: Int) {
            if (position == 0) setImage(binding.ivEgg)
            binding.title = level
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem
        }
    }
}