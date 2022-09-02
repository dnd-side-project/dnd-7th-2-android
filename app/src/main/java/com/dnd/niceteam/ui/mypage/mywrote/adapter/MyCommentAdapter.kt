package com.dnd.niceteam.ui.mypage.mywrote.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnd.niceteam.databinding.ItemMyCommentBinding
import com.dnd.niceteam.ui.mypage.mywrote.viewmodel.MyWroteViewModel

class MyCommentAdapter(private val viewModel: MyWroteViewModel) :
    ListAdapter<MyComment, MyCommentAdapter.MyCommentViewHolder>(diffCallback) {

    init {
        setHasStableIds(true)
    }

    inner class MyCommentViewHolder(val binding: ItemMyCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            with(binding) {
                layoutContainer.setOnClickListener {
                    if (viewModel.isDelete.value) {
                        val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                            ?: return@setOnClickListener
                        cbDelete.isChecked = !cbDelete.isChecked
                        if (cbDelete.isChecked) viewModel.addDeleteCommentId(position)
                        else viewModel.removeDeleteCommentId(position)
                    }
                }
            }
        }

        fun bind(position: Int) {
            with(binding) {
                myComment = getItem(position)
                cbDelete.apply {
                    isChecked = viewModel.deleteCommentIds.value.contains(position)
                    visibility = if (viewModel.isDelete.value) View.VISIBLE else View.GONE
                }
            }
        }
    }

    fun selectAll() {
        val ids = mutableListOf<Int>()
        for (i in 0 until viewModel.comments.value.size) {
            ids.add(i)
        }
        viewModel.setDeleteCommentIds(ids)
        notifyDataSetChanged()
    }

    fun unselectAll() {
        viewModel.setDeleteCommentIds(listOf())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCommentViewHolder {
        val binding =
            ItemMyCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyCommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyCommentViewHolder, position: Int) {
        holder.bind(position)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MyComment>() {
            override fun areItemsTheSame(oldItem: MyComment, newItem: MyComment): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MyComment, newItem: MyComment): Boolean =
                oldItem == newItem
        }
    }
}

data class MyComment(
    val id: Int,
    val comment: String,
    val date: String,
    val type: String,
    val title: String
)