package com.dnd.niceteam.ui.mypage.applicationstatus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnd.niceteam.databinding.ItemApplicationStatusBinding

class ApplicationStatusAdapter :
    ListAdapter<Model, ApplicationStatusAdapter.ApplicationStatusViewHolder>(diffCallback) {

    inner class ApplicationStatusViewHolder(val binding: ItemApplicationStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Model) {
            with(binding) {
                model = data
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationStatusViewHolder {
        val binding =
            ItemApplicationStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ApplicationStatusViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApplicationStatusViewHolder, position: Int) {
        holder.bind(getItem(position))
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
    val subTitle: String,
    val type: String
)