package com.dnd.niceteam.ui.mypage.myteample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnd.niceteam.databinding.ItemMyTeampleBinding
import com.dnd.niceteam.ui.mypage.myteample.viewmodel.MyTeampleViewModel

class MyTeampleAdapter(
    private val viewModel: MyTeampleViewModel,
    private val clickedContainer: () -> Unit,
    private val clickedReview: () -> Unit
) : ListAdapter<MyTeample, MyTeampleAdapter.MyTeampleViewHolder>(diffCallback) {

    inner class MyTeampleViewHolder(private val binding: ItemMyTeampleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            with(binding) {
                layoutContainer.setOnClickListener {
                    val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                        ?: return@setOnClickListener
                    clickedContainer()
                }
                btnWriteReview.setOnClickListener {
                    clickedReview()
                }
            }
        }

        fun bind(data: MyTeample) {
            with(binding) {
                tabPosition = viewModel.tabPosition.value
                myTeample = data
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTeampleViewHolder {
        val binding =
            ItemMyTeampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyTeampleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyTeampleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MyTeample>() {
            override fun areItemsTheSame(oldItem: MyTeample, newItem: MyTeample) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MyTeample, newItem: MyTeample) =
                oldItem == newItem
        }
    }
}

data class MyTeample(
    val id: Int,
    val type: String,
    val name: String,
    val projectCategory: String,
    val professorName: String,
    val lectureTime: String,
    val personnel: String
)
