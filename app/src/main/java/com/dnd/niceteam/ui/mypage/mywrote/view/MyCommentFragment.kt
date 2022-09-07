package com.dnd.niceteam.ui.mypage.mywrote.view

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dnd.niceteam.R
import com.dnd.niceteam.base.BaseFragment
import com.dnd.niceteam.databinding.FragmentMyCommentBinding
import com.dnd.niceteam.ui.mypage.mywrote.adapter.MyCommentAdapter
import com.dnd.niceteam.ui.mypage.mywrote.viewmodel.MyWroteViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MyCommentFragment : BaseFragment<FragmentMyCommentBinding>(R.layout.fragment_my_comment) {

    private val viewModel: MyWroteViewModel by activityViewModels()
    private val adapter: MyCommentAdapter by lazy { MyCommentAdapter(viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        initView()
        observeData()
    }

    private fun bind() {
        with(binding) {
            viewModel = this@MyCommentFragment.viewModel
            adapter = this@MyCommentFragment.adapter
        }
    }

    private fun initView() {
        with(binding) {
            btnCancel.setOnClickListener {
                this@MyCommentFragment.viewModel.changeDeleteMode()
            }
            btnTop.setOnClickListener {
                clickedTopButton()
            }
            btnDelete.setOnClickListener {
                this@MyCommentFragment.viewModel.apply {
                    deleteComments()
                    changeDeleteMode()
                }
            }
        }
    }

    private fun observeData() {
        with(viewModel) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    comments.collectLatest { list ->
                        adapter.submitList(list)
                        binding.tvCommentCount.text = "전체 ${list.size}"
                        binding.layoutEmpty.visibility =
                            if (list.isEmpty()) View.VISIBLE else View.GONE
                    }
                }
            }
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    isDelete.collectLatest {
                        updateView()
                        adapter.notifyDataSetChanged()
                    }
                }
            }
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    deleteCommentIds.collectLatest {
                        updateView()
                        binding.btnDelete.isEnabled = it.isNotEmpty()
                    }
                }
            }
        }
    }

    private fun clickedTopButton() {
        when (binding.btnTop.text) {
            getString(R.string.label_delete_comment) -> viewModel.changeDeleteMode()
            getString(R.string.label_select_all) -> adapter.selectAll()
            getString(R.string.label_unselect) -> adapter.unselectAll()
            else -> return
        }
    }

    private fun updateView() {
        with(binding) {
            val isDelete = this@MyCommentFragment.viewModel.isDelete.value
            val comments = this@MyCommentFragment.viewModel.comments.value
            val deleteIds = this@MyCommentFragment.viewModel.deleteCommentIds.value
            if (isDelete) {
                btnDelete.apply {
                    visibility = View.VISIBLE
                    isEnabled = deleteIds.isNotEmpty()
                }
                btnCancel.visibility = View.VISIBLE
                btnTop.apply {
                    text =
                        if (comments.size != deleteIds.size) getString(R.string.label_select_all)
                        else getString(R.string.label_unselect)
                    setTextColor(ContextCompat.getColor(context, R.color.primary_purple))
                }
            } else {
                btnDelete.visibility = View.GONE
                btnCancel.visibility = View.GONE
                btnTop.apply {
                    text = getString(R.string.label_delete_comment)
                    setTextColor(ContextCompat.getColor(context, R.color.gray_5))
                }
            }
        }
    }
}