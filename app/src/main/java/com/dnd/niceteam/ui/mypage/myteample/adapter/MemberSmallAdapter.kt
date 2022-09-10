package com.dnd.niceteam.ui.mypage.myteample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnd.niceteam.databinding.ItemMemberSmallBinding
import com.dnd.niceteam.ui.mypage.myteample.view.Member

class MemberSmallAdapter :
    ListAdapter<Member, MemberSmallAdapter.MemberSmallViewHolder>(diffCallback) {

    inner class MemberSmallViewHolder(private val binding: ItemMemberSmallBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Member) {
            with(binding) {
                member = data
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberSmallViewHolder {
        val binding =
            ItemMemberSmallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemberSmallViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemberSmallViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Member>() {
            override fun areItemsTheSame(oldItem: Member, newItem: Member) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Member, newItem: Member) =
                oldItem == newItem
        }
    }
}