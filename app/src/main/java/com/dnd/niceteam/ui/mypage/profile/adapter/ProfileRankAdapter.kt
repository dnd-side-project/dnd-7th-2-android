package com.dnd.niceteam.ui.mypage.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnd.niceteam.databinding.ItemProfileRankBinding
import com.dnd.niceteam.ui.mypage.profile.viewmodel.ProfileViewModel

class ProfileRankAdapter(val viewModel: ProfileViewModel) :
    ListAdapter<Model, ProfileRankAdapter.ProfileRankViewHolder>(diffCallback) {

    inner class ProfileRankViewHolder(val binding: ItemProfileRankBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Model) {
            with(binding) {
                model = data
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileRankViewHolder {
        val binding =
            ItemProfileRankBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileRankViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileRankViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return if (viewModel.allProfileRank.value || currentList.size < 3) super.getItemCount() else 3
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
    val rank: Int,
    val detail: String,
    val count: String
)