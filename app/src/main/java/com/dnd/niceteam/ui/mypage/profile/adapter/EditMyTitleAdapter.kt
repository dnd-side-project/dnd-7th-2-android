package com.dnd.niceteam.ui.mypage.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnd.niceteam.databinding.ItemEditMyTitleBinding

class EditMyTitleAdapter(
    private val selectTitle: String,
    private val clickedItem: (item: MyTitle) -> Unit
) : ListAdapter<MyTitle, EditMyTitleAdapter.EditMyTitleViewHolder>(diffCallback) {

    inner class EditMyTitleViewHolder(val binding: ItemEditMyTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            with(binding) {
                root.setOnClickListener {
                    val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                        ?: return@setOnClickListener
                    clickedItem(getItem(position))
                }
            }
        }

        fun bind(data: MyTitle) {
            with(binding) {
                title = data
                isSelected = data.title == selectTitle
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditMyTitleViewHolder {
        val binding =
            ItemEditMyTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EditMyTitleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EditMyTitleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MyTitle>() {
            override fun areItemsTheSame(oldItem: MyTitle, newItem: MyTitle): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: MyTitle, newItem: MyTitle): Boolean =
                oldItem == newItem
        }
    }
}

data class MyTitle(
    val image: String,
    val title: String
)