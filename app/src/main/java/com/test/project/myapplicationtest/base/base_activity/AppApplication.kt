package com.test.project.myapplicationtest.base.base_activity

import android.app.Application
import com.test.project.myapplicationtest.base.data_call.ApiManager

/**
 * Created by Harshal Chaudhari on 16/06/21.
 */
class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ApiManager.handleNetworkCallWithCache(this)
    }
}