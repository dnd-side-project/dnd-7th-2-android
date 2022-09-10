package com.dnd.niceteam.ui.mypage.myteample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnd.niceteam.R
import com.dnd.niceteam.databinding.ItemMemberBinding
import com.dnd.niceteam.ui.mypage.myteample.view.Member
import com.dnd.niceteam.ui.mypage.myteample.viewmodel.MyTeampleDetailViewModel

class MemberAdapter(
    private val myTeampleDetailViewModel: MyTeampleDetailViewModel,
    private val clicked: (position: Int) -> Unit
) : ListAdapter<Member, MemberAdapter.MemberViewHolder>(diffCallback) {

    private val firstVote = myTeampleDetailViewModel.teampleDetail.value.firstVote

    init {
        setHasStableIds(true)
    }

    inner class MemberViewHolder(private val binding: ItemMemberBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            with(binding) {
                ivProfile.setOnClickListener {
                    if (firstVote) {
                        val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                            ?: return@setOnClickListener
                        val exportPosition = myTeampleDetailViewModel.exportMemberPosition.value

                        if (exportPosition == null) {
                            myTeampleDetailViewModel.updateExportMemberPosition(position)
                        } else {
                            if (exportPosition == position) {
                                myTeampleDetailViewModel.updateExportMemberPosition(null)
                            } else {
                                myTeampleDetailViewModel.updateExportMemberPosition(position)
                                notifyItemChanged(exportPosition)
                            }
                        }
                        notifyItemChanged(position)
                        clicked(position)
                    }
                }
            }
        }

        fun bind(position: Int) {
            with(binding) {
                val member = getItem(position)
                val export = myTeampleDetailViewModel.exportMemberPosition.value == position
                ivProfile.setImageResource(
                    when (member.level) {
                        1 -> if (!export) R.drawable.ic_level_1_member_profile else R.drawable.ic_level_1_member_profile_selected
                        2 -> if (!export) R.drawable.ic_level_2_member_profile else R.drawable.ic_level_2_member_profile_selected
                        3 -> if (!export) R.drawable.ic_level_3_member_profile else R.drawable.ic_level_3_member_profile_selected
                        4 -> if (!export) R.drawable.ic_level_4_member_profile else R.drawable.ic_level_4_member_profile_selected
                        else -> R.drawable.ic_level_1_member_profile
                    }
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val binding =
            ItemMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.bind(position)
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