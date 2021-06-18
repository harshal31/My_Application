package com.test.project.myapplicationtest.ui.main_activity

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.test.project.myapplicationtest.base.Result
import com.test.project.myapplicationtest.base.UserResponse
import com.test.project.myapplicationtest.base.base_view_model.BaseViewModel
import com.test.project.myapplicationtest.room.UserIdentity
import com.test.project.myapplicationtest.ui.util.mutableLiveData
import com.test.project.myapplicationtest.ui.util.mutableLiveDataOf
import com.test.project.myapplicationtest.ui.util.retroCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

/**
 * Created by Harshal Chaudhari on 16/06/21.
 */
class MainViewModel(application: Application) : BaseViewModel(application) {

    val userResponseState = mutableLiveDataOf<UserResponse>()
    val itemClick = mutableLiveData<Result>()
    var prevPosition = 0

    val adapter = UserAdapter(this)

    fun callUserResponse(limit: Int) {
        viewModelScope.launch {
            retroCall({ apiManager.getUserData(limit) }) {
                userResponseState.postValue(it)
            }
        }
    }

    fun insertDataIntoDatabase(result: Result) {
        viewModelScope.launch {
            val userIdentity = UserIdentity(userId = result.login?.uuid ?: "", name = result.name?.first ?: "", result.liked)
            roomManager.getUserDao().insertUser(userIdentity)
        }
    }


    fun getUserById(id: String): Flow<UserIdentity?> {
        return roomManager.getUserDao()
            .getUserById(id)
            .flowOn(Dispatchers.Default)
    }

}