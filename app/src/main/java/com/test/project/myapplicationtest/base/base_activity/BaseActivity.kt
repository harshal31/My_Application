package com.test.project.myapplicationtest.base.base_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.test.project.myapplicationtest.BR
import com.test.project.myapplicationtest.base.base_view_model.BaseViewModel

/**
 * Created by Harshal Chaudhari on 16/06/21.
 */


abstract class BaseActivity<B: ViewDataBinding>: AppCompatActivity() {

    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
    }


    open fun setUpUi() {
        binding.setVariable(BR.viewModel, viewModel)
    }

    open fun setUpListener() {}

    open fun listenMutableEvents() {}

    abstract val layoutId: Int
    abstract val viewModel: BaseViewModel
}