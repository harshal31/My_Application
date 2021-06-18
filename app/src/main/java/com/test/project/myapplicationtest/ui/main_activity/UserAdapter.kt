package com.test.project.myapplicationtest.ui.main_activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.project.myapplicationtest.R
import com.test.project.myapplicationtest.base.Result
import com.test.project.myapplicationtest.databinding.UserViewHolderBinding
import com.test.project.myapplicationtest.ui.util.logger

/**
 * Created by Harshal Chaudhari on 17/06/21.
 */
class UserAdapter(private val viewModel: MainViewModel) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val userList = mutableListOf<Result>()

    fun addData(data: List<Result>) {
        userList.addAll(data)
        notifyDataSetChanged()
    }

    fun updatedUser(pos: Int, result: Result) {
        userList[pos] = result
        notifyItemChanged(pos, result)
    }

    fun getList() = userList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = DataBindingUtil.inflate<UserViewHolderBinding>(LayoutInflater.from(parent.context), R.layout.user_view_holder, parent, false)
        return UserViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) = holder.bindData(userList[position])

    override fun getItemCount() = userList.size

    override fun getItemId(position: Int) = position.toLong()


    inner class UserViewHolder(private val binding: UserViewHolderBinding, private val viewModel: MainViewModel) : RecyclerView.ViewHolder(binding.root) {

        init {
            handleClick()
        }

        fun bindData(res: Result) {
            binding.result = res
        }

        private fun handleClick() {
            binding.likeBtn.setOnClickListener {
                binding.likeBtn.isLiked = !binding.likeBtn.isLiked
                logger("binfi = ${binding.likeBtn.isLiked}")
                binding.result?.liked = binding.likeBtn.isLiked
                viewModel.itemClick.value = binding.result
            }
        }
    }
}

