package com.dnd.niceteam.ui.mypage.mywrote.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnd.niceteam.databinding.ItemMyRecruitmentBinding

class MyRecruitmentAdapter(
    private val clickedPullUp: () -> Unit,
    private val clickedCompletion: () -> Unit
) : ListAdapter<MyRecruitment, MyRecruitmentAdapter.MyRecruitmentViewHolder>(diffCallback) {

    inner class MyRecruitmentViewHolder(private val binding: ItemMyRecruitmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            with(binding) {
                btnFirst.setOnClickListener {    // 끌올 또는 재모집
                    val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                        ?: return@setOnClickListener
                    val status = getItem(position).status
                    if (status == "모집중") clickedPullUp()
                }
                btnSecond.setOnClickListener {   // 모집완료
                    clickedCompletion()
                }
            }
        }

        fun bind(data: MyRecruitment) {
            with(binding) {
                myRecruitment = data
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecruitmentViewHolder {
        val binding =
            ItemMyRecruitmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyRecruitmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyRecruitmentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MyRecruitment>() {
            override fun areItemsTheSame(oldItem: MyRecruitment, newItem: MyRecruitment) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MyRecruitment, newItem: MyRecruitment) =
                oldItem == newItem
        }
    }
}

data class MyRecruitment(
    val id: Int,
    val status: String,
    val type: String,
    val title: String
)