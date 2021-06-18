package com.test.project.myapplicationtest.base.base_view_model

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.test.project.myapplicationtest.base.data_call.ApiHelper
import com.test.project.myapplicationtest.base.data_call.ApiManager
import com.test.project.myapplicationtest.room.UserDatabase
import com.test.project.myapplicationtest.ui.util.ServerStatus
import com.test.project.myapplicationtest.ui.util.mutableLiveData

/**
 * Created by Harshal Chaudhari on 16/06/21.
 */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val apiManager: ApiHelper = ApiManager
    val roomManager = UserDatabase.getInstance(application)
    val serverStatus = mutableLiveData(ServerStatus.ON_NONE)

}