package com.dnd.niceteam.ui.onboarding.signup.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnd.niceteam.databinding.ItemSearchBinding

class SearchAdapter(
    private val onClickItem: (String) -> Unit
) : ListAdapter<String, SearchViewHolder>(searchDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchBinding.inflate(inflater, parent, false)
        return SearchViewHolder(binding, onClickItem)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class SearchViewHolder(
    private val binding: ItemSearchBinding,
    private val onClickItem: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(name: String) {
        itemView.setOnClickListener { onClickItem(name) }
        binding.name = name
    }
}

val searchDiffUtil = object : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}