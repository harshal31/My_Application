package com.test.project.myapplicationtest.ui.main_activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.test.project.myapplicationtest.R
import com.test.project.myapplicationtest.base.base_activity.BaseActivity
import com.test.project.myapplicationtest.databinding.ActivityMainBinding
import com.test.project.myapplicationtest.ui.util.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId = R.layout.activity_main
    override val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpUi()
        setUpListener()
        listenMutableEvents()

        viewModel.serverStatus.observe(this) {
            logger("statusssss $it")
        }
    }

    override fun setUpUi() {
        super.setUpUi()
        binding.imagePager.orientation = ViewPager2.ORIENTATION_HORIZONTAL


        viewModel.adapter.setHasStableIds(true)
        binding.imagePager.adapter = viewModel.adapter
        viewModel.callUserResponse(10)
    }

    override fun setUpListener() {
        binding.imagePager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.onPagination(position) {
                    viewModel.callUserResponse(it)
                }
                checkDataIsResideInDatabase(position)
            }
        })

    }

    private fun checkDataIsResideInDatabase(position: Int) {
        lifecycleScope.launch {
            val result = viewModel.adapter.getList()[position]
            viewModel.getUserById(result.login?.uuid ?: "").collect {
                if (it != null) {
                    result.liked = it.liked
                    viewModel.adapter.updatedUser(position, result)
                }
            }
        }
    }

    override fun listenMutableEvents() {
        viewModel.userResponseState.observeOnce(this) {
            onSuccess {
                viewModel.adapter.addData(it.results)
            }
            onFailure {
                logger(it)
            }
        }

        viewModel.itemClick.observe(this) {
            it?.apply {
                viewModel.insertDataIntoDatabase(it)
            }
        }


    }

}